package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.repository.CompletedChallengesDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CompletedChallengesViewModel(application: Application, username: String) :
    AndroidViewModel(application) {

    var dataSourceFactory = CompletedChallengesDataSourceFactory(CoroutineScope(Dispatchers.IO), username)

    private val config = PagedList.Config.Builder()
        .setPageSize(30)
        .setEnablePlaceholders(false)
        .build()

    private val _completedChallenges = LivePagedListBuilder<Int, CompletedChallenge>(dataSourceFactory, config)
    val completedChallenges: LivePagedListBuilder<Int, CompletedChallenge>
        get() = _completedChallenges
}