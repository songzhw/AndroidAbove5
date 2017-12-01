package six.ca.and8.autofill

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import six.ca.and8.R

class AutoFillDemo : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);

        btnLogin.setOnClickListener({
            startActivity(Intent(this@AutoFillDemo, AutoFillDemo::class.java))
        })

    }
}
