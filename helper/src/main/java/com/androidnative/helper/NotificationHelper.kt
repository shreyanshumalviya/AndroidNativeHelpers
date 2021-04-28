package com.androidnative.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper {
    companion object {
        var notificationId = 0;
        const val TAG = "NotificationHelper"
        public fun sendNotification(context: Context, message: String) :Int{
            notificationId++;
            return sendNotification(
                context,
                null,
                message,
                R.drawable.ic_baseline_notifications_none_24,
                notificationId
            )
        }

        public fun sendNotification(context: Context, title: String?, message: String):Int {
            notificationId++
            return sendNotification(
                context, title, message, R.drawable.ic_baseline_notifications_none_24,
                notificationId
            )
        }

        public fun sendNotification(context: Context, message: String,notificationId: Int) :Int{
            return sendNotification(
                context,
                null,
                message,
                R.drawable.ic_baseline_notifications_none_24,
                notificationId
            )
        }

        public fun sendNotification(context: Context, title: String?, message: String,notificationId: Int):Int {
            return sendNotification(
                context, title, message, R.drawable.ic_baseline_notifications_none_24,
                notificationId
            )
        }

        public fun sendNotification(
            context: Context,
            title: String?,
            message: String,
            notificationDrawable: Int,
            notificationId: Int
        ) :Int {
            var builder = NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(notificationDrawable)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", "name", importance).apply {
                description = message
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
//                val notificationId = 0
                notify(notificationId, builder.build())
            }
            return notificationId
        }
    }
}