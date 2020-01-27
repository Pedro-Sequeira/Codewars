package com.example.android.codewars.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.android.codewars.models.Challenge

class ChallengeDataSourceFactory(private val username: String, private val challengeType: String) :
    DataSource.Factory<Int, Challenge>() {

    private val liveDataSource = MutableLiveData<ChallengeDataSource>()

    override fun create(): DataSource<Int, Challenge> {
        val dataSource = ChallengeDataSource(username, challengeType)
        liveDataSource.postValue(dataSource)

        return dataSource
    }
}