package six.ca.and8.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class EightService : Service() {
	override fun onBind(intent: Intent): IBinder {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun onCreate() {
		super.onCreate()
		println("szw EightService.onCreate()")
	}

	override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		println("szw EightService.onStartCmd()")
		return super.onStartCommand(intent, flags, startId)
	}

	override fun onDestroy() {
		println("szw EightService.onDestroy()")
		super.onDestroy()
	}
}

/*
额外知识点:
1. 一个服务只会创建一次，销毁一次，但是会开始多次
onCreate(): 除非调用了 ctx.stopService(), 或 this.stopSelf(), 它就只会被调用一次.
onStartCommand(): service已经创建了, 还没有销毁之前, 多次调用 ctx.startService(), onCreate()不会被多次调用, onStartCmd()会
2.
 */