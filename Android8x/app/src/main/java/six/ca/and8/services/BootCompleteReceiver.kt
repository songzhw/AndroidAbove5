package six.ca.and8.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BootCompleteReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context, intent: Intent) {
		println("szw BootCompleteReceiver.onReceive()")

		val it = Intent(context, EightService::class.java)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			context.startForegroundService(it)
		} else {
			context.startService(it)
		}
	}
}