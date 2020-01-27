package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.codewars.R
import com.example.android.codewars.databinding.FragmentChallengesBinding
import com.example.android.codewars.repository.AUTHORED_TYPE
import com.example.android.codewars.repository.COMPLETED_TYPE
import com.example.android.codewars.viewModels.ChallengeViewModel
import com.example.android.codewars.viewModels.ChallengesViewModelFactory
import com.example.android.codewars.views.adapters.ChallengeAdapter

class ChallengesFragment : Fragment() {

    private lateinit var binding: FragmentChallengesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChallengesBinding.inflate(inflater)

        val arguments = ChallengesFragmentArgs.fromBundle(arguments!!)
        val username = arguments.username

        val viewModel = initViewModel(username)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        initAdapter(viewModel)

        viewModel.navigateToChallengeDetails.observe(this, Observer { challengeId ->
            challengeId?.let {
                this.findNavController().navigate(
                    ChallengesFragmentDirections
                        .actionCompletedChallengesFragmentToChallengeDetailsFragment(challengeId)
                )
                viewModel.onChallengeDetailsNavigated()
            }
        })

        binding.navigationBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_completed -> {
                    viewModel.refreshChallengeList(COMPLETED_TYPE)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_authored -> {
                    viewModel.refreshChallengeList(AUTHORED_TYPE)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        return binding.root
    }

    private fun initViewModel(username: String): ChallengeViewModel {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ChallengesViewModelFactory(application, username)

        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(ChallengeViewModel::class.java)
    }

    private fun initAdapter(viewModel: ChallengeViewModel) {
        val adapter = ChallengeAdapter(ChallengeAdapter.ChallengeClickListener { challengeId ->
            viewModel.onChallengeClicked(challengeId)
        })
        binding.challengesList.adapter = adapter

        viewModel.challenges.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}