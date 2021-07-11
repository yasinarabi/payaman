package ir.yasinarabi.smartsms

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.widget.Toast
import ir.yasinarabi.smartsms.models.Bank

class SmsService: Service() {
    private lateinit var receiver: SmsReceiver
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        receiver = SmsReceiver(this)
        registerReceiver(receiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
        val sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE)
        with(sharedPreferences.edit())
        {
            putBoolean("Running", true)
            apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        val sharedPreferences = getSharedPreferences("Main", Context.MODE_PRIVATE)
        with(sharedPreferences.edit())
        {
            putBoolean("Running", false)
            apply()
        }
    }
}