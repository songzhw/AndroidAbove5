package ca.six.jet.core.http

import okhttp3.*
import java.io.IOException

object Http {
    val client = OkHttpClient()

    fun get(url: String, succCallback: (String) -> Unit) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("szw http fail: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                succCallback(response.body?.string() ?: "")
            }
        })
    }
}