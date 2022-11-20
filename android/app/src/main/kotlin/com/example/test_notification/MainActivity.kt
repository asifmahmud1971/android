package com.example.test_notification

import android.content.Intent
import android.os.Build
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private var forService: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forService = Intent(this@MainActivity, MyService::class.java)
        flutterEngine?.dartExecutor?.let {
            MethodChannel(it.binaryMessenger, "com.example.test_notification")
                .setMethodCallHandler { methodCall, result ->
                    if (methodCall.method == "startService") {
                        startService()

                        result.success("Service Started")
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(forService)
    }

    private fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(forService)
        } else {
            startService(forService)
        }
    }
}

