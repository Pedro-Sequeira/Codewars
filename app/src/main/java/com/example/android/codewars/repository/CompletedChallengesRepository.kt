package com.example.android.codewars.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.network.PAGE_SIZE

class CompletedChallengesRepository {

    lateinit var completedChallengesPagedList: LiveData<PagedList<CompletedChallenge>>
    lateinit var completedChallengesDataSourceFactory: CompletedChallengesDataSourceFactory

    fun fetchCompletedChallengesPagedList(username: String): LiveData<PagedList<CompletedChallenge>> {
        completedChallengesDataSourceFactory = CompletedChallengesDataSourceFactory(username)

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        completedChallengesPagedList =
            LivePagedListBuilder(completedChallengesDataSourceFactory, config).build()

        return completedChallengesPagedList
    }
}