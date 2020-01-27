package com.example.android.codewars.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.android.codewars.models.Challenge
import com.example.android.codewars.network.API_FIRST_PAGE
import com.example.android.codewars.network.CodewarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val COMPLETED_TYPE = "completed"
const val AUTHORED_TYPE = "authored"

class ChallengeDataSource(private val username: String, private val challengeType: String) :
    PageKeyedDataSource<Int, Challenge>() {

    private val apiService = CodewarsApi.retrofitService
    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Challenge>
    ) {
        coroutineScope.launch {
            try {
                when (challengeType) {
                    COMPLETED_TYPE -> {
                        val response = apiService.getCompletedChallenges(username)
                        val challenges = response?.data

                        callback.onResult(
                            challenges ?: listOf(),
                            null,
                            API_FIRST_PAGE + 1
                        )
                    }
                    AUTHORED_TYPE -> {
                        val response = apiService.getAutheredChallenges(username)
                        val challenges = response?.data

                        callback.onResult(
                            challenges ?: listOf(),
                            null,
                            API_FIRST_PAGE + 1
                        )
                    }
                }

            } catch (exception: Exception) {
                Log.e("ChallengesDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Challenge>) {
        coroutineScope.launch {
            try {
                when (challengeType) {
                    COMPLETED_TYPE -> {
                        val response = apiService.getCompletedChallenges(username, params.key)
                        val challenges = response?.data
                        callback.onResult(
                            challenges ?: listOf(),
                            params.key + 1
                        )
                    }
                    AUTHORED_TYPE -> {
                        val response = apiService.getAutheredChallenges(username)
                        val challenges = response?.data
                        callback.onResult(
                            challenges ?: listOf(),
                            params.key + 1
                        )
                    }
                }

            } catch (exception: Exception) {
                Log.e("ChallengesDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Challenge>) {
        coroutineScope.launch {
            try {
                when (challengeType) {
                    COMPLETED_TYPE -> {
                        val response = apiService.getCompletedChallenges(username, params.key)
                        val challenges = response?.data
                        callback.onResult(
                            challenges ?: listOf(),
                            params.key - 1
                        )
                    }
                    AUTHORED_TYPE -> {
                        val response = apiService.getAutheredChallenges(username)
                        val challenges = response?.data
                        callback.onResult(
                            challenges ?: listOf(),
                            params.key - 1
                        )
                    }
                }

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