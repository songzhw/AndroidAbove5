package ca.six.io2018.biz.homelist

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.six.io2018.R

class AddActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        viewModel = ViewModelProviders.of(this).get(HomeListViewModel::class.java)

    }


}
