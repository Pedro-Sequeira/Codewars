package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.codewars.network.CodewarsApi
import com.example.android.codewars.repository.UsersRepository
import com.example.android.codewars.repository.database.UsersDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = UsersDatabase.getInstance(application)
    private val usersRepository = UsersRepository(database.usersDao, CodewarsApi.retrofitService)

    val users = usersRepository.users
    val status = usersRepository.status

    fun fetchUser(query: String?) {
        coroutineScope.launch {
            usersRepository.fetchUser(query)
        }
    }

    fun orderByRank() {
        users.value?.sortedByDescending {
            it.score
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}