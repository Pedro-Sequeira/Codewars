package com.example.android.codewars.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.codewars.R
import com.example.android.codewars.adapters.UserAdapter
import com.example.android.codewars.databinding.FragmentSearchBinding
import com.example.android.codewars.viewModels.SearchViewModel
import com.example.android.codewars.viewModels.SearchViewModelFactory

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

        val adapter = UserAdapter()
        binding.usersList.adapter = adapter

        setHasOptionsMenu(true)

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
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}