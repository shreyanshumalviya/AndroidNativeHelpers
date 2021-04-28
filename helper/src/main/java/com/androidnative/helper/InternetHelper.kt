package com.androidnative.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class InternetHelper {
    companion object{
        private val TAG: String?="InternetHelper"

        fun isConnected(context: Context):Boolean{
            var connected: Boolean
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connected =
                connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
            return connected
        }

        fun downloadFile(downloadUrl: String){
            val url = URL(downloadUrl);
            val c: HttpURLConnection = url.openConnection() as HttpURLConnection

            c.requestMethod = "GET";
            c.connect();
            val apkStorage = File(
                Environment.getExternalStorageDirectory().toString() + "/Downloads"
            )

            if (!apkStorage.exists()) {
                apkStorage.mkdir();
                Log.e(TAG, "Directory Created.");
            }
            var downloadFileName:String = downloadUrl
            val outputFile = File(apkStorage, downloadFileName)
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.e(TAG, "File Created");
            }
            val fos = FileOutputStream(outputFile) //Get OutputStream for NewFile Location


            val inputStream: InputStream = c.inputStream //Get InputStream for connection


            val buffer = ByteArray(1024) //Set buffer type

            var len1 = 0 //init length

            while (inputStream.read(buffer).also { len1 = it } != -1) {
                fos.write(buffer, 0, len1) //Write new file
            }
            fos.close();
            inputStream.close();
        }
    }
}