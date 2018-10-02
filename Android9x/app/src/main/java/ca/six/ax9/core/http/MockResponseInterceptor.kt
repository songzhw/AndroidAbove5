package ca.six.ax9.core.http

import okhttp3.*

class MockResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (HttpEngine.isMock) {
            val jsonContent = HttpEngine.mockJson
            val responseBody = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded"), jsonContent)

            //TODO 后期要新加一个"fail"的情形, 不能光是成功. 这可能得HttpEngine新加一个isMockedFail情形.
            return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(HttpEngine.CODE_SUCCESS)
                    .message("ok")
                    .body(responseBody)
                    .build()
        } else {
            return chain.proceed(request)
        }
    }
}