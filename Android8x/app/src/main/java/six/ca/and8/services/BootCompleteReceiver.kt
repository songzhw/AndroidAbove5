package six.ca.and8.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompleteReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context, intent: Intent) {
		println("szw BootCompleteReceiver.onReceive()")
		val it = Intent(context, EightService::class.java)
		context.startService(it)
	}
}