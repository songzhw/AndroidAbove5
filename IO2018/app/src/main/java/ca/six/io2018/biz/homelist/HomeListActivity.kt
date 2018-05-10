package ca.six.io2018.biz.homelist

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ca.six.io2018.R

class HomeListActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(HomeListViewModel::class.java)

    }


}
