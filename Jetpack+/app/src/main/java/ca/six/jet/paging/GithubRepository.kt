package ca.six.jet.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GithubRepository(private val api: IGithubService) {
    val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    fun searchAndStream(query: String): Flow<PagingData<Repo>> {
        val pagingSource = GithubPagingSource(api, query)
        val pager = Pager(config = pagingConfig, pagingSourceFactory = { pagingSource })
        return pager.flow
    }

}