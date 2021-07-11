package ir.yasinarabi.smartsms

import android.app.Activity
import android.content.*
import android.provider.Telephony
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import ir.yasinarabi.smartsms.models.Bank

class SmsReceiver(private val service: SmsService): BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?) {
        for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent))
        {
            for (bank in banks)
            {
                if (sms.displayOriginatingAddress.equals(bank.number))
                {
                    val regex = bank.pattern.toRegex()
                    val matchResult = regex.find(sms.displayMessageBody)
                    if (matchResult != null)
                    {
                        val (pass) = matchResult.destructured
                        val clipboardManager = service.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clipData = ClipData.newPlainText("text", pass)
                        clipboardManager.setPrimaryClip(clipData)
                        Toast.makeText(context, service.getString(R.string.copied), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}