package ca.six.jet

import android.app.Application
import ca.six.jet.room.relation.RecipeDatabaseProvider
import com.facebook.stetho.Stetho

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        val db = RecipeDatabaseProvider.db(this)

    }
}