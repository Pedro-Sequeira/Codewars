package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.codewars.models.User
import com.example.android.codewars.network.CodewarsApi
import com.example.android.codewars.repository.UsersRepository
import com.example.android.codewars.repository.database.UsersDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchUserViewModel(application: Application) :
    AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = UsersDatabase.getInstance(application)
    private val usersRepository = UsersRepository(database.usersDao, CodewarsApi.retrofitService)

    private val _navigateToChallenges = MutableLiveData<String>()
    val navigateToChallenges: LiveData<String>
        get() = _navigateToChallenges

    private val dbUsers = usersRepository.users

    private val _users = MediatorLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    val status = usersRepository.status

    init {
        _users.addSource(dbUsers) {
            it?.let {
                _users.value = it
            }
        }
    }

    fun fetchUser(query: String?) {
        coroutineScope.launch {
            usersRepository.fetchUser(query)
        }
    }

    fun orderByRank() {
        _users.value = dbUsers.value?.sortedByDescending {
            it.score
        }
    }

    fun onUserClicked(username: String) {
        _navigateToChallenges.value = username
    }

    fun onChallengesNavigated() {
        _navigateToChallenges.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}