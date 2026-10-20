package ar.edu.unlam.mobile.scaffolding.ui.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import ar.edu.unlam.mobile.scaffolding.ui.helpers.createImageFile
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ShutterButton() {
    // 1) Obtenemos el contexto actual
    val context = LocalContext.current

    // 2) Creamos un archivo de imagen
    // Para crear un archivo de imagen, necesitamos una función que nos permita crear un archivo
    // temporal en el dispositivo. Esta función la podemos encontrar en el helper createImageFile
    // que creamos previamente.
    // Esta función nos devuelve un archivo de imagen con un nombre único y una extensión .jpg.
    val file = context.createImageFile()

    // 3) Obtenemos la URI del archivo. La URI del archivo es un Identificador Unido de Recursos.
    // En criollo significa que es la ubicación del archivo en el dipositivo.
    // Necesitamos pasarle al helper getUriForFile 3 parámetros:
    //  - El contexto
    //  - El nombre del authority provider que definimos en el AndroidManifest.xml
    // (        <provider
    //            android:name="androidx.core.content.FileProvider"
    //            android:authorities="ar.edu.unlam.mobile.scaffolding.provider"
    //            android:exported="false"
    //            android:grantUriPermissions="true">
    //            <meta-data
    //                android:name="android.support.FILE_PROVIDER_PATHS"
    //                android:resource="@xml/provider_paths" />
    //        </provider>)
    // https://developer.android.com/training/secure-file-sharing/setup-sharing
    // - El file que creamos previamente
    val uri =
        FileProvider.getUriForFile(
            context,
            "ar.edu.unlam.mobile.scaffolding.provider",
            file,
        )

    // 4) Creamos un estado mutable para almacenar la URI de la imagen capturada
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    // 5) Creamos un launcher para capturar la imagen
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    // 6) Creamos un launcher para solicitar permisos
    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) {
            if (it) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            val permissionCheckResult =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                cameraLauncher.launch(uri)
            } else {
                // Request a permission
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }) {
            Text(text = "Capture Image From Camera")
        }
        if (capturedImageUri.path?.isNotEmpty() == true) {
            Image(
                modifier =
                    Modifier
                        .padding(16.dp, 8.dp),
                painter = rememberAsyncImagePainter(capturedImageUri),
                contentDescription = null,
            )
        }
    }
}
