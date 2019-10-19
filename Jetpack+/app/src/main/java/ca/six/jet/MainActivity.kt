package ca.six.jet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import ca.six.jet.security.SecurityDemo
import ca.six.jet.viewmodel.LV_Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        PeriodicWorkRequest.Builder() // Periodic work has a minimum interval of 15 minutes and it cannot have an initial delay.

        btnSecurity.setOnClickListener { startActivity(Intent(this, SecurityDemo::class.java)) }
        btnLV.setOnClickListener { startActivity(Intent(this, LV_Activity::class.java)) }

    }
}
