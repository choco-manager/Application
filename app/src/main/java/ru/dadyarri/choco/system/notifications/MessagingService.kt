package ru.dadyarri.choco.system.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("FCM", "FCM token updated $token")
    }
}