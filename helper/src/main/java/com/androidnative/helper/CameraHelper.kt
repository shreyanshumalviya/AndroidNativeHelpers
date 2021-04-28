package com.androidnative.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraHelper {
    companion object {

        public fun captureVideo(context: Activity,REQUEST_INTENT_RESULT:Int):File?{
            return captureCameraEvent(context,REQUEST_INTENT_RESULT,true)
        }
        public fun captureImage(context: Activity,REQUEST_INTENT_RESULT:Int):File?{
            return captureCameraEvent(context,REQUEST_INTENT_RESULT,false)
        }

        private fun captureCameraEvent(context: Activity,REQUEST_INTENT_RESULT:Int,vid: Boolean): File? {
            var mediaStore = MediaStore.ACTION_IMAGE_CAPTURE
            if (vid) mediaStore= MediaStore.ACTION_VIDEO_CAPTURE
            val takePictureIntent = Intent(mediaStore)
            if (takePictureIntent.resolveActivity(context.packageManager) != null) {
                // Create the File where the photo should go
//                try {
                    val photoFile = createImageFile(context,vid)
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        val photoURI = FileProvider.getUriForFile(
                            context,
                            "com.androidnative.helper.fileprovider",
                            photoFile!!
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        context.startActivityForResult(takePictureIntent, REQUEST_INTENT_RESULT)

                    }
            return photoFile/*
                } catch (ex: Exception) {
                    // Error occurred while creating the File
//                        displayMessage(baseContext, ex.message.toString())
                }*/

            } else {
//                    displayMessage(baseContext, "Null")
            }
//            }
            return null;
        }

        @Throws(IOException::class)
        private fun createImageFile(context: Context, vid:Boolean): File {
            // Create an image file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            var imageFileName = "JPEG_" + timeStamp + "_"
            if (vid)imageFileName="VIDEO"+timeStamp+"_"
            var suffix=".jpg"
            if(vid)suffix=".mp4"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File.createTempFile(
                imageFileName, /* prefix */
                suffix, /* suffix */
                storageDir      /* directory */
            )

            // Save a file: path for use with ACTION_VIEW intents
            val mCurrentPhotoPath = image.absolutePath
            Log.d("tfgvk",mCurrentPhotoPath)
            return image
        }


    }

}