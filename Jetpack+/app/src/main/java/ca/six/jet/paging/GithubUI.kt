package ca.six.jet.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.cachedIn
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ca.six.jet.R
import kotlinx.android.synthetic.main.activity_et_rv.*
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
    private var searchJob: Job? = null
    lateinit var adapter: GithubAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvResult.layoutManager = LinearLayoutManager(this)
        rvResult.addItemDecoration(decoration)
        adapter = GithubAdapter()
        rvResult.adapter = adapter

        search("android")
    }

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.search(repo, query)
                .collectLatest {
                    adapter.submitData(it) //在lifecycle里, 就不用调用adapter.submit(lifecycle, it)了
                }
        }
    }

}

val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem.url == newItem.url
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem == newItem
}

class GithubAdapter : PagingDataAdapter<Repo, GithubViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder = GithubViewHolder.create(parent)

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val datum = getItem(position)
        if (datum != null) {
            holder.bind(datum)
        }
    }

}

class GithubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_name)
    private val description: TextView = view.findViewById(R.id.repo_description)
    private val stars: TextView = view.findViewById(R.id.repo_stars)
    private val language: TextView = view.findViewById(R.id.repo_language)
    private val forks: TextView = view.findViewById(R.id.repo_forks)

    private var repo: Repo? = null

    companion object {
        fun create(parent: ViewGroup): GithubViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv, parent, false)
            return GithubViewHolder(view)
        }
    }

    fun bind(repo: Repo?) {
        if (repo != null) {
            this.repo = repo
            name.text = repo.fullName

            // if the description is missing, hide the TextView
            var descriptionVisibility = View.GONE
            if (repo.description != null) {
                description.text = repo.description
                descriptionVisibility = View.VISIBLE
            }
            description.visibility = descriptionVisibility

            stars.text = repo.stars.toString()
            forks.text = repo.forks.toString()

            // if the language is missing, hide the label and the value
            var languageVisibility = View.GONE
            if (!repo.language.isNullOrEmpty()) {
                val resources = this.itemView.context.resources
                language.text = resources.getString(R.string.language, repo.language)
                languageVisibility = View.VISIBLE
            }
            language.visibility = languageVisibility
        }
    }
}