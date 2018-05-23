package ca.six.io2018.biz.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import ca.six.io2018.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val viewWhole = inflater.inflate(R.layout.fragment_main, container, false)
        val tvMainMessage = viewWhole.findViewById<TextView>(R.id.tvMainMessage)
        tvMainMessage.setOnClickListener { tv ->
            // findNavController(view): @param view -- the view to search from
            Navigation.findNavController(viewWhole)
                    .navigate(R.id.nav_action_main_to_settings)
        }
        return viewWhole
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}
