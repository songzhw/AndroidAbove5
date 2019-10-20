package ca.six.jet.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.six.jet.Platform

class LV_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProviders.of(this).get(LV_ViewModel::class.java)
        vm.fetch()

        vm.getPlatforms().observe(this, Observer<List<Platform>> {Platforms ->
            println("szw actv thread = ${Thread.currentThread().name}")
        })
    }
}