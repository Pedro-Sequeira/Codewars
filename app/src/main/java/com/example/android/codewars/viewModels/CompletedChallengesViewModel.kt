package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.repository.CompletedChallengesRepository

class CompletedChallengesViewModel(application: Application, username: String) :
    AndroidViewModel(application) {

    private val repository = CompletedChallengesRepository()

    private val _navigateToChallengeDetails = MutableLiveData<String>()
    val navigateToChallengeDetails: LiveData<String>
        get() = _navigateToChallengeDetails


    val completedChallenges: LiveData<PagedList<CompletedChallenge>> by lazy {
        repository.fetchCompletedChallengesPagedList(username)
    }

    fun onChallengeClicked(id: String) {
        _navigateToChallengeDetails.value = id
    }

    fun onChallengeDetailsNavigated() {
        _navigateToChallengeDetails.value = null
    }
}