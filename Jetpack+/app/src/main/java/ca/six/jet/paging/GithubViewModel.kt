package ca.six.jet.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class GithubViewModel(private val repo: GithubRepository) : ViewModel() {
    fun search(query: String): Flow<PagingData<Repo>> {
        return repo.searchAndStream(query).cachedIn(viewModelScope)
    }
}