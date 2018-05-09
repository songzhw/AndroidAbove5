package ca.six.io2018.biz.homelist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.six.io2018.R

class HomeLIstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeListFragment.newInstance())
                    .commitNow()
        }
    }



}
