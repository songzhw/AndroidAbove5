package ca.six.io2018.biz.homelist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.six.io2018.R

class HomeListFragment : Fragment() {

    companion object {
        fun newInstance() = HomeListFragment()
    }

    private lateinit var viewModel: HomeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
