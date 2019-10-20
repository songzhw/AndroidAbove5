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

        // add this if statement, then it will not fetch back-end data again when we rotate the screen
        if (vm.getPlatforms().value == null) {
            vm.fetch()
        }

        vm.getPlatforms().observe(this, Observer<List<Platform>> { platforms ->
            println("szw actv thread = ${Thread.currentThread().name}") //=> thread = main (因为vm中使用了postValue(), 而不是setValue())
            println("szw actv list = $platforms")
            // tv.text = platforms.toString()
        })
    }
}