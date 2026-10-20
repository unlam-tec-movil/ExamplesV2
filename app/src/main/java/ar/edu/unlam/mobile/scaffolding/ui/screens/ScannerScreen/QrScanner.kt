package ar.edu.unlam.mobile.scaffolding.ui.screens.ScannerScreen

import android.Manifest
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

val SCANNER_SCREEN_ROUTE = "scanner"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen() {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var scannedValue by remember { mutableStateOf<String?>(null) }

    if (!cameraPermissionState.status.isGranted) {
        // Ask once; in a real app trigger this from a Button click / LaunchedEffect.
        LaunchedEffectPermission(cameraPermissionState)
        Surface(Modifier.fillMaxSize()) {
            Text("Camera permission is required to scan QR codes.", Modifier.padding(16.dp))
        }
        return
    }

    Box(Modifier.fillMaxSize()) {
        CameraPreviewWithScanner(
            onBarcodeDetected = { value -> scannedValue = value },
        )

        scannedValue?.let { value ->
            Surface(
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
            ) {
                Text("Scanned: $value", Modifier.padding(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LaunchedEffectPermission(state: PermissionState) {
    LaunchedEffect(Unit) {
        state.launchPermissionRequest()
    }
}

@Composable
private fun CameraPreviewWithScanner(onBarcodeDetected: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview =
                    Preview.Builder().build().also {
                        it.surfaceProvider = previewView.surfaceProvider
                    }

                val scannerOptions =
                    BarcodeScannerOptions
                        .Builder()
                        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                        .build()
                val scanner = BarcodeScanning.getClient(scannerOptions)

                val imageAnalysis =
                    ImageAnalysis
                        .Builder()
                        .setTargetResolution(Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(ContextCompat.getMainExecutor(ctx)) { imageProxy ->
                                processImageProxy(scanner, imageProxy, onBarcodeDetected)
                            }
                        }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview,
                        imageAnalysis,
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
    )
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    scanner: BarcodeScanner,
    imageProxy: ImageProxy,
    onBarcodeDetected: (String) -> Unit,
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        scanner
            .process(image)
            .addOnSuccessListener { barcodes ->
                barcodes.firstOrNull()?.rawValue?.let { onBarcodeDetected(it) }
            }.addOnCompleteListener {
                imageProxy.close()
            }
    } else {
        imageProxy.close()
    }
}
