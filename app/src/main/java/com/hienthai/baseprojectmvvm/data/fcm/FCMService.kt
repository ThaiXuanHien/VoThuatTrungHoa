package com.hienthai.baseprojectmvvm.data.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FCMService : FirebaseMessagingService() {
    private val pushNotification: PushNotificationManager by inject()
    private val notificationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        notificationScope.launch {
            pushNotification.onMessageReceived(message)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        notificationScope.launch {

        }
    }
}