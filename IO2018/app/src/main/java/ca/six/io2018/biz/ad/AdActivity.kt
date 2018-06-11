package ca.six.io2018.biz.ad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AdActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onSupportNavigateUp(): Boolean {
        // return navController.navigationUp()
        return super.onSupportNavigateUp()
    }
}


