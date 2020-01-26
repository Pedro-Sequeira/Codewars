package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CompletedChallengesViewModelFactory(
    private val application: Application,
    private val username: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthoredChallengesViewModel::class.java)) {
            return AuthoredChallengesViewModel(application, username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}