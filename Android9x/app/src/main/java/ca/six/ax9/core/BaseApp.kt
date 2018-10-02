package ca.six.ax9.core

import android.app.Application
import android.content.Context
import android.os.Handler
import kotlin.properties.Delegates

class BaseApp : Application() {

    companion object {
        var appContext : Context by Delegates.notNull<Context>()
        val handler = Handler()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}