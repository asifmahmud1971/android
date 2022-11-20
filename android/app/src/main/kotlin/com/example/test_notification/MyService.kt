package com.example.test_notification

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import org.jetbrains.annotations.Nullable

class MyService : Service() {
    @Override
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder: Notification.Builder = Notification.Builder(this, "messages")
                .setContentText("This is running in Background")
                .setContentTitle("Flutter Background")
                .setSmallIcon(R.drawable.ic_android_black_24dp)

            startForeground(101, builder.build())
        }
    }

    @Nullable
    @Override
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}