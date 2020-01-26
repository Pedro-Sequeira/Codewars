package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.codewars.databinding.FragmentCompletedChallengesBinding
import com.example.android.codewars.viewModels.CompletedChallengesViewModel
import com.example.android.codewars.viewModels.CompletedChallengesViewModelFactory
import com.example.android.codewars.views.adapters.CompletedChallengeAdapter

class CompletedChallengesFragment : Fragment() {

    private lateinit var binding: FragmentCompletedChallengesBinding
    private lateinit var viewModel: CompletedChallengesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCompletedChallengesBinding.inflate(inflater)

        val arguments = CompletedChallengesFragmentArgs.fromBundle(arguments!!)
        val username = arguments.username

        viewModel = initViewModel(username)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        initAdapter()

        viewModel.navigateToChallengeDetails.observe(this, Observer { challengeId ->
            challengeId?.let {
                this.findNavController().navigate(
                    CompletedChallengesFragmentDirections
                        .actionCompletedChallengesFragmentToChallengeDetailsFragment(challengeId)
                )
                viewModel.onChallengeDetailsNavigated()
            }
        })

        return binding.root
    }

    private fun initViewModel(username: String): CompletedChallengesViewModel {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = CompletedChallengesViewModelFactory(application, username)

        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(CompletedChallengesViewModel::class.java)
    }

    private fun initAdapter() {
        val adapter =
            CompletedChallengeAdapter(CompletedChallengeAdapter.ChallengeClickListener { challengeId ->
                viewModel.onChallengeClicked(challengeId)
            })
        binding.completedChallengesList.adapter = adapter

        viewModel.completedChallenges.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}