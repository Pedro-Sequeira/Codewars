package com.example.android.codewars.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.android.codewars.models.CompletedChallenge

class CompletedChallengesDataSourceFactory(private val username: String) :
    DataSource.Factory<Int, CompletedChallenge>() {

    private val liveDataSource = MutableLiveData<CompletedChallengesDataSource>()

    override fun create(): DataSource<Int, CompletedChallenge> {
        val dataSource = CompletedChallengesDataSource(username)
        liveDataSource.postValue(dataSource)

        return dataSource
    }
}