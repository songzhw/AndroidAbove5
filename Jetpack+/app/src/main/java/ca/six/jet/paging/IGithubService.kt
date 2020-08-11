package ca.six.jet.paging

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IGithubService {
    // page是从1开始的. (若page=0, 效果与page=1一样)
    @GET("search/repositories?sort=stars")
    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): RepoSearchResponse
}

object GithubService {
    private val BASE_URL = "https://api.github.com/"
    val api: IGithubService

    init {
        val httpClient = OkHttpClient.Builder().build() //这可加interceptor
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IGithubService::class.java)
    }
}

