package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.codewars.databinding.FragmentAuthoredChallengesBinding
import com.example.android.codewars.viewModels.AuthoredChallengesViewModel
import com.example.android.codewars.viewModels.AuthoredChallengesViewModelFactory
import com.example.android.codewars.views.adapters.AuthoredChallengeAdapter

class AuthoredChallengesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAuthoredChallengesBinding.inflate(inflater)

        val arguments = AuthoredChallengesFragmentArgs.fromBundle(arguments!!)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = AuthoredChallengesViewModelFactory(application, arguments.username)

        val challengesViewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(AuthoredChallengesViewModel::class.java)

        binding.viewModel = challengesViewModel

        binding.lifecycleOwner = this

        binding.authoredChallengesList.adapter = AuthoredChallengeAdapter()

        return binding.root
    }
}