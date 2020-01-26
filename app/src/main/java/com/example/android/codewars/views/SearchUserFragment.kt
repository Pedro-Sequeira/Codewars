package com.example.android.codewars.views

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.codewars.R
import com.example.android.codewars.databinding.FragmentSearchUserBinding
import com.example.android.codewars.views.adapters.UserAdapter
import com.example.android.codewars.viewModels.SearchUserViewModel
import com.example.android.codewars.viewModels.SearchUserViewModelFactory

class SearchUserFragment : Fragment(), SearchView.OnQueryTextListener {

    private val searchViewModel: SearchUserViewModel by lazy {
        ViewModelProviders.of(
            this,
            SearchUserViewModelFactory(requireNotNull(this.activity).application)
        ).get(SearchUserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchUserBinding.inflate(inflater)

        binding.viewModel = searchViewModel

        binding.lifecycleOwner = this

        binding.usersList.adapter = UserAdapter(UserAdapter.UserClickListener { username ->
            searchViewModel.onUserClicked(username)
        })

        setHasOptionsMenu(true)

        searchViewModel.navigateToChallenges.observe(this, Observer { username ->
            username?.let {
                this.findNavController().navigate(
                    SearchUserFragmentDirections
                        .actionSearchFragmentToCompletedChallengesFragment(username)
                )
                searchViewModel.onChallengesNavigated()
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
        searchViewModel.fetchUser(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}