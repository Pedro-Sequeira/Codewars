package com.example.android.codewars.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.codewars.models.Challenge
import com.example.android.codewars.network.PAGE_SIZE

class ChallengesRepository {

    private lateinit var pagedList: LiveData<PagedList<Challenge>>
    private lateinit var dataSourceFactory: ChallengeDataSourceFactory

    fun fetchChallengePagedList(
        username: String,
        challengeType: Int
    ): LiveData<PagedList<Challenge>> {
        dataSourceFactory = ChallengeDataSourceFactory(username, challengeType)

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        pagedList = LivePagedListBuilder(dataSourceFactory, config).build()

        return pagedList
    }
}