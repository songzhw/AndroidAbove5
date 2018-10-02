package ca.six.ax9.core.http

import android.support.annotation.WorkerThread
import ca.six.ax9.core.BaseApp
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


object HttpEngine {
    val CODE_SUCCESS = 200

    // for t androidTest
    var isMock = false
    var mockJson = ""
    var isFinished = false;

    // config info
    val PREFIX = "http://192.168.2.26:8899/"  // Node.js runs on my Mac

    val http: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(MockResponseInterceptor())
                .build()
    }


    // kotlin比python好, 有默认值的参数不用在最后
    fun request(apiName: String,
                formData : RequestBody? = null,
                @WorkerThread onResp: (payload: JSONObject) -> Unit
                ) {
        println("szw http 00. req = $apiName")
        isFinished = false
        val requestBuilder = Request.Builder()
                .url(PREFIX + apiName)

        if(formData != null){
            requestBuilder.post(formData)
        }

        val req = requestBuilder.build()

        println("szw http 01")
        http.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println("szw err = $e") //TODO 后续可以统一处理请求过程中的failure
                isFinished = true
            }

            override fun onResponse(call: Call, resp: Response) {
                val respStr = resp.body()?.string() ?: "" // 三目运算符
                val respJson = JSONObject(respStr)
                println("szw http 02 ${respStr.substring(0, 55)}")

                val code = respJson.optInt("code")
                println("szw http 03 code = $code")

                if(code == CODE_SUCCESS) {
                    println("szw http 04 -- resp branch")
                    val payload = respJson.opt("payload") as JSONObject
                    onResp(payload)
                } else {
                    println("szw http 05 -- error branch")
                    val errorMsg = respJson.optString("msg")
                    // run on the main thread
                    BaseApp.handler.post {
                        println("error: ${errorMsg}")
                    }

                }
                isFinished = true
            }

        })

    }

}