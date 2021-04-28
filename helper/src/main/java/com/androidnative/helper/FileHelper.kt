package com.androidnative.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract

class FileHelper {
    companion object {
        public fun openFile(context: Activity, pickerInitialUri: Uri, requestResponseCode: Int) {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/pdf"

                // Optionally, specify a URI for the file that should appear in the
                // system file picker when it loads.
                putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
            }

            context.startActivityForResult(intent, requestResponseCode)
        }
    }
}