package com.example.android.codewars.repository

import androidx.paging.DataSource
import com.example.android.codewars.models.CompletedChallenge
import kotlinx.coroutines.CoroutineScope

class CompletedChallengesDataSourceFactory(private val coroutineScope: CoroutineScope, private val username: String)
    : DataSource.Factory<Int, CompletedChallenge>() {
    override fun create(): DataSource<Int, CompletedChallenge> {
        return CompletedChallengesDataSource(coroutineScope, username)
    }
}