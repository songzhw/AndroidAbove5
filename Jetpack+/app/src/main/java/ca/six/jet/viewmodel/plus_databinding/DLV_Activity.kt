package ca.six.jet.viewmodel.plus_databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ca.six.jet.R
import ca.six.jet.databinding.ActivityDlvBinding

class DLV_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(DLV_ViewModel::class.java)

        val binding: ActivityDlvBinding = DataBindingUtil.setContentView(this, R.layout.activity_dlv)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        if (viewModel.user.value == null) {
            viewModel.fetch()
        }

        println("szw actv 01 : name = ${viewModel.name}")


    }
}