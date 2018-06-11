package ca.six.io2018.biz.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.six.io2018.R
import kotlinx.android.synthetic.main.activity_settings.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("id", -100)
        tvSingleTv.text = "Profile : $id, $name"

    }

}
