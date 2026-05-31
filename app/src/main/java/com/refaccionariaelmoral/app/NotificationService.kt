package com.refaccionariaelmoral.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {
    
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        
        // Handle FCM messages here
        val title = remoteMessage.notification?.title ?: "Refaccionaria El Moral"
        val body = remoteMessage.notification?.body ?: "You have a new notification"
        
        sendNotification(title, body)
    }
    
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Send token to your server if needed
        sendTokenToServer(token)
    }
    
    private fun sendNotification(title: String, messageBody: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // Create notification channel for Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Refaccionaria Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications from Refaccionaria El Moral"
            }
            notificationManager.createNotificationChannel(channel)
        }
        
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
    
    private fun sendTokenToServer(token: String) {
        // Implement this to send the FCM token to your backend
        // This allows you to send targeted notifications
    }
    
    companion object {
        private const val CHANNEL_ID = "refaccionaria_notifications"
        private const val NOTIFICATION_ID = 1
    }
}
