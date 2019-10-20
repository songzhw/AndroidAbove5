package ca.six.jet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.six.jet.Platform
import ca.six.jet.core.http.Http
import org.json.JSONArray
import org.json.JSONObject

// the liveData will know the value even when you rotate the screen
class LV_ViewModel : ViewModel() {
    private val platforms: MutableLiveData<List<Platform>> = MutableLiveData()

    fun fetch() {
        Http.get("http://www.mocky.io/v2/5dab51b03100002d00beceaa") { resp ->
            println("szw resp thread = ${Thread.currentThread().name}") //=> thread = OkHttp http://www.mocky.io/...
            val json = (JSONObject(resp).get("payload") as JSONObject).get("platforms") as JSONArray
            val list = ArrayList<Platform>()
            for (i in 0 until json.length()) {
                val obj = json.get(i) as JSONObject
                list.add(Platform(obj.getInt("id"), obj.getString("name")))
            }
            platforms.postValue(list)
        }
    }

    fun getPlatforms(): LiveData<List<Platform>> {
        return platforms
    }
}

/*

// 1. 一开始进入页面
szw resp thread = OkHttp http://www.mocky.io/...
szw actv thread = main
szw actv list = [Platform(id=1, name=android), Platform(id=2, name=iOS), Platform(id=3, name=react native)]

// 2. rotate the screen
szw actv list = [Platform(id=1, name=android), Platform(id=2, name=iOS), Platform(id=3, name=react native)]
szw actv thread = main
szw actv list = [Platform(id=1, name=android), Platform(id=2, name=iOS), Platform(id=3, name=react native)]

 */