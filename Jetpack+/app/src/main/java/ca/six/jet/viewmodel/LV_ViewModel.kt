package ca.six.jet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.six.jet.Platform
import ca.six.jet.core.http.Http
import org.json.JSONArray
import org.json.JSONObject

class LV_ViewModel : ViewModel() {
    private lateinit var platforms: MutableLiveData<List<Platform>>

    fun fetch() {
        Http.get("http://www.mocky.io/v2/5dab51b03100002d00beceaa") { resp ->
            println("szw resp thread = ${Thread.currentThread().name}") //=> thread = OkHttp http://www.mocky.io/...
            val json = (JSONObject(resp).get("payload") as JSONObject).get("platforms") as JSONArray
            val list = ArrayList<Platform>()
            for(i in 0 until json.length()) {
                println("szw $i")
                val obj = json.get(i) as JSONObject
                list.add(Platform(obj.getInt("id"), obj.getString("name")))
            }
            println("szw $list")
        }
    }

    fun getPlatforms(): LiveData<List<Platform>> {
        return platforms
    }
}