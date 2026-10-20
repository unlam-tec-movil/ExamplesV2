package ar.edu.unlam.mobile.scaffolding.ui.helpers

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class ImageType {
    JPEG,
    PNG,
    GIF,
}

fun Context.createImageFile(format: ImageType = ImageType.JPEG): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "${format.name}_" + timeStamp + "_"
    val image =
        File.createTempFile(
            imageFileName, // prefix
            ".${format.name.lowercase()}", // suffix
            externalCacheDir, // directory
        )
    return image
}
