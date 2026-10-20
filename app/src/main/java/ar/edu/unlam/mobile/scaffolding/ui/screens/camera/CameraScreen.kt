package ar.edu.unlam.mobile.scaffolding.ui.screens.camera

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.ShutterButton
import ar.edu.unlam.mobile.scaffolding.ui.screens.ScannerScreen.SCANNER_SCREEN_ROUTE

val CAMERA_SCREEN_ROUTE = "camera"

@Composable
fun CameraScreen(controller: NavHostController) {
    ShutterButton()
    Button(onClick = { controller.navigate(SCANNER_SCREEN_ROUTE) }) {
        Text("Scan")
    }
}
