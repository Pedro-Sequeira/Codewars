package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.repository.CompletedChallengesRepository

class CompletedChallengesViewModel(application: Application, username: String) :
    AndroidViewModel(application) {

    private val repository = CompletedChallengesRepository()

    val completedChallenges: LiveData<PagedList<CompletedChallenge>> by lazy {
        repository.fetchCompletedChallengesPagedList(username)
    }
}