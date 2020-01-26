package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.codewars.databinding.FragmentChallengeDetailsBinding
import com.example.android.codewars.viewModels.ChallengeDetailsViewModel
import com.example.android.codewars.viewModels.ChallengeDetailsViewModelFactory


class ChallengeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentChallengeDetailsBinding.inflate(inflater)

        val arguments = ChallengeDetailsFragmentArgs.fromBundle(arguments!!)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ChallengeDetailsViewModelFactory(application, arguments.challengeId)

        val viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ChallengeDetailsViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}