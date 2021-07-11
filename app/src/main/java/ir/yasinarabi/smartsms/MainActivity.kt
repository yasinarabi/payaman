package ir.yasinarabi.smartsms

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.GridView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import ir.yasinarabi.smartsms.adapters.BankAdapter
import ir.yasinarabi.smartsms.models.Bank

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS), 1)
        }
        else
        {
            receiveMsg()
        }
        val gridView = findViewById<GridView>(R.id.main_gv)
        val adapter = BankAdapter(this, banks)
        gridView.adapter = adapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            receiveMsg()
    }

    private fun receiveMsg() {
        val sharedPref = getSharedPreferences("Main", Context.MODE_PRIVATE)
        val running = sharedPref.getBoolean("Running", false)
        if (! running)
        {
            val i = Intent(this, SmsService::class.java)
            startService(i)
        }
    }
}
