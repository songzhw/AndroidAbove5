package ca.six.jet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.six.jet.security.SecurityDemo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSecurity.setOnClickListener { startActivity(Intent(this, SecurityDemo::class.java)) }
    }
}
