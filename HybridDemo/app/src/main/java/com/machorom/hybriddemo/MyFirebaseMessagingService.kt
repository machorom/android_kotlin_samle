package com.machorom.hybriddemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "FirebaseService"
    override fun onNewToken(token: String) {
        Log.d(TAG,"onNewToken : $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG,"onMessageReceived : ${remoteMessage.from}")
        Log.d(TAG, "Message data payload: " + remoteMessage.data)
        var intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        if ( remoteMessage.data.isNotEmpty() ) {
            intent.putExtra("click_action", remoteMessage.data["click_action"])
            intent.putExtra("action_url", remoteMessage.data["action_url"])
        }
        if( remoteMessage.notification != null){
            Log.d(TAG,"notification body : ${remoteMessage.notification?.title}, ${remoteMessage.notification?.body}")
            sendNotification(remoteMessage.notification?.title, remoteMessage.notification?.body, intent)
        }
    }

    /**
     * android P이상의 디바이스에서는 channel 개념이 추가되어서 channel을 만들어야 한다.
     * */
    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean,
                                          name: String, description: String):String {
        val channelId = "${context.packageName}-$name"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
        return channelId
    }

    private fun sendNotification(title:String?, body: String?, intent: Intent) {
        val channelId = createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false, getString(R.string.app_name), "App notification channel")

        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(notificationSound)
            .setContentIntent(pendingIntent)
        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}
