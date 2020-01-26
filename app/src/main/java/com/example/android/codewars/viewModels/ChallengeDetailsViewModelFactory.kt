package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChallengeDetailsViewModelFactory(
    private val application: Application,
    private val challengeId: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengeDetailsViewModel::class.java)) {
            return ChallengeDetailsViewModel(application, challengeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}