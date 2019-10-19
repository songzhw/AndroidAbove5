package ca.six.jet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.six.jet.Platform
import ca.six.jet.core.http.Http
import org.json.JSONObject

class LV_ViewModel : ViewModel() {
    private lateinit var platforms: MutableLiveData<List<Platform>>

    fun fetch() {
        Http.get("http://www.mocky.io/v2/5dab51b03100002d00beceaa") { resp ->
            val json = JSONObject(resp)
            println("szw : resp = $json")
        }
    }

    fun getPlatforms(): LiveData<List<Platform>> {
        return platforms
    }
}