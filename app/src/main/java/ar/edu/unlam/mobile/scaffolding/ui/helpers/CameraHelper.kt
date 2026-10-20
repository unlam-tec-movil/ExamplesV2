package ar.edu.unlam.mobile.scaffolding.ui.helpers

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import ar.edu.unlam.mobile.scaffolding.domain.photo.PhotoUseCases
import jakarta.inject.Inject

class CameraHelper
    @Inject
    constructor(
        private val activity: ComponentActivity,
    ) : PhotoUseCases {
// Handle the returned Uri

        override fun takePhoto() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                    // Handle the returned Uri
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
