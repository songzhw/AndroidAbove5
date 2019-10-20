package ca.six.jet.viewmodel.plus_databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.six.jet.User
import ca.six.jet.core.http.Http
import org.json.JSONObject

class DLV_ViewModel : ViewModel() {
    val user = MutableLiveData<User>()

    fun fetch() {
        Http.get("http://www.mocky.io/v2/5dabb3a53000005f00298617") { resp ->
            println("szw resp thread = ${Thread.currentThread().name}") //=> thread = OkHttp http://www.mocky.io/...
            val json = JSONObject(resp).get("user") as JSONObject
            val userInResp = User(json.getInt("id"), json.getString("name"))
            println("szw DLV_vm $userInResp")
            user.postValue(userInResp)
        }
    }
}