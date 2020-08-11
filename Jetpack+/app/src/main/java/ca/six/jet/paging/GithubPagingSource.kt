package ca.six.jet.paging

import androidx.paging.PagingSource
import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)

data class Repo(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?
)

private const val BEGIN_PAGE = 1 //github page API is 1 based

class GithubPagingSource(private val service: IGithubService, private val query: String) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val page = params.key ?: BEGIN_PAGE // load()第一次被call时, params.key是null的值. 所以这里要加一个默认值
        val apiQuery = query + "in:name,description" //限定只在库的名字与描述中查找字样
        return try {  //注意, kotlin里 return里也可以有try-catch了!
            val response = service.searchRepo(apiQuery, page, params.loadSize)
            val data = response.items
            val prevPage = if (page == BEGIN_PAGE) null else page - 1
            val nextPage = if (data.isEmpty()) null else page + 1
            LoadResult.Page(data, prevPage, nextPage) //得有: 数据 + 上一页id + 下一页id
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}