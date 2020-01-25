package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.codewars.databinding.FragmentChallengesBinding
import com.example.android.codewars.views.adapters.ChallengesAdapter
import com.example.android.codewars.viewModels.ChallengesViewModel
import com.example.android.codewars.viewModels.ChallengesViewModelFactory

class ChallengesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChallengesBinding.inflate(inflater)

        val arguments = ChallengesFragmentArgs.fromBundle(arguments!!)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ChallengesViewModelFactory(application, arguments.username)

        val challengesViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ChallengesViewModel::class.java)

        binding.viewModel = challengesViewModel

        binding.lifecycleOwner = this

        binding.challengesList.adapter = ChallengesAdapter()

        setHasOptionsMenu(true)

        return binding.root
    }
}