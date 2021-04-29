package com.androidnative.helpers

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.androidnative.helper.InternetHelper
import com.androidnative.helper.CameraHelper
import com.androidnative.helper.FileHelper
import com.androidnative.helper.NotificationHelper
import java.io.File

class MainActivity : AppCompatActivity() {
    private val CAPTURE_IMAGE_REQUEST: Int =0
    private var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val notificationId = NotificationHelper.sendNotification(this,"Title","App Launched Successfully");
//        Thread.sleep(5000)
        NotificationHelper.sendNotification(this,"Title","Notification sent",notificationId);
//        CameraHelper.captureImage(this)

    }
    fun openCamera(view: View){
//        photoFile = CameraHelper.captureImage(this,CAPTURE_IMAGE_REQUEST)

        FileHelper.openFile(this, Uri.parse("/storage/emulated/0/"),2)
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
                val myBitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)
//                imageView.setImageBitmap(myBitmap)
            } else {
//                displayMessage(baseContext, "Request cancelled or something went wrong.")
            }
        }
}