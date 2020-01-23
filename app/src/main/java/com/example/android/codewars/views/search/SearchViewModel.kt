package com.example.android.codewars.views.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.codewars.database.UsersDatabase
import com.example.android.codewars.domainModels.User
import com.example.android.codewars.network.CodewarsApi
import com.example.android.codewars.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val database = UsersDatabase.getInstance(application)

    private val usersRepository =
        UsersRepository(database.usersDao, CodewarsApi.retrofitService)

    init {
        coroutineScope.launch {
            usersRepository.users
        }
    }

    val users = usersRepository.users

    fun getUser(query: String?) {
        coroutineScope.launch {
            usersRepository.getUser(query)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}