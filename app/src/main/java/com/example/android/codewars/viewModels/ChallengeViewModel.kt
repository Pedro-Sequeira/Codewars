package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.codewars.models.Challenge
import com.example.android.codewars.network.ApiStatus
import com.example.android.codewars.repository.COMPLETED_TYPE
import com.example.android.codewars.repository.ChallengesRepository

class ChallengeViewModel(application: Application, private val username: String) :
    AndroidViewModel(application) {

    private val repository = ChallengesRepository()

    var challenges: LiveData<PagedList<Challenge>>

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        challenges = repository.fetchChallengePagedList(username, COMPLETED_TYPE)
    }

    fun refreshChallengeList(challengeType: String) {
        challenges = repository.fetchChallengePagedList(username, challengeType)
    }

    private val _navigateToChallengeDetails = MutableLiveData<String>()
    val navigateToChallengeDetails: LiveData<String>
        get() = _navigateToChallengeDetails

    fun onChallengeClicked(id: String) {
        _navigateToChallengeDetails.value = id
    }

    fun onChallengeDetailsNavigated() {
        _navigateToChallengeDetails.value = null
    }
}