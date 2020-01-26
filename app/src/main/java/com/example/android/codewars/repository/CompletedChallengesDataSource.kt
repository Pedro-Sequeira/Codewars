package com.example.android.codewars.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.network.API_FIRST_PAGE
import com.example.android.codewars.network.CodewarsApi
import kotlinx.coroutines.*

class CompletedChallengesDataSource(private val username: String)
    : PageKeyedDataSource<Int, CompletedChallenge>() {

    private val apiService = CodewarsApi.retrofitService
    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, CompletedChallenge>) {
        coroutineScope.launch {
            try {
                val response = apiService.getCompletedChallenges(username)
                val completedChallenges = response?.data
                callback.onResult(
                    completedChallenges ?: listOf(),
                    null,
                    API_FIRST_PAGE + 1
                )
            } catch (exception: Exception) {
                Log.e("ChallengesDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CompletedChallenge>) {
        coroutineScope.launch {
            try {
                val response = apiService.getCompletedChallenges(username, params.key)
                val completedChallenges = response?.data
                callback.onResult(
                    completedChallenges ?: listOf(),
                    params.key + 1
                )
            } catch (exception: Exception) {
                Log.e("ChallengesDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CompletedChallenge>) {
        coroutineScope.launch {
            try {
                val response = apiService.getCompletedChallenges(username, params.key)
                val completedChallenges = response?.data
                callback.onResult(
                    completedChallenges ?: listOf(),
                    params.key - 1
                )
            } catch (exception: Exception) {
                Log.e("ChallengesDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}