package com.example.android.codewars.views.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codewars.R
import com.example.android.codewars.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = SearchViewModelFactory(application)
        val searchViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SearchViewModel::class.java)
        binding.viewModel = searchViewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        val adapter = UserAdapter()
        binding.usersList.adapter = adapter

        searchViewModel.users.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                binding.usersList.scrollToPosition(0)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.getUser(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}