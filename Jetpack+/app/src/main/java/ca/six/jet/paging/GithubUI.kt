package ca.six.jet.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ca.six.jet.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GithubViewModel : ViewModel() {
    fun search(repo: GithubRepository, query: String): Flow<PagingData<Repo>> {
        return repo.searchAndStream(query).cachedIn(viewModelScope)
    }
}

class GithubActivity : AppCompatActivity(R.layout.activity_et_rv) {
    // 这个by viewModels, 可要求gradle中是:  kotlinOptions {jvmTarget = JavaVersion.VERSION_1_8.toString() }
    private val vm: GithubViewModel by viewModels()
    private val api = GithubService.api
    private val repo = GithubRepository(api)
    private var searchJob : Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    fun search(query: String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.search(repo, query)
                .collectLatest {
                    adapter.submitDat(it) //在lifecycle里, 就不用调用adapter.submit(lifecycle, it)了
                }
        }
    }

}