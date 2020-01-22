package com.example.android.codewars.views.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.codewars.database.DatabaseUser
import com.example.android.codewars.database.UserDatabase
import com.example.android.codewars.models.User
import com.example.android.codewars.network.CodewarsApi
import com.example.android.codewars.repository.UsersRepository
import kotlinx.coroutines.*

class SearchViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val database = UserDatabase.getInstance(application)

    val users = database.userDatabaseDao.getAllUsers()

    private val usersRepository = UsersRepository(database)

    init {
        coroutineScope.launch {
            database.userDatabaseDao.getAllUsers()
        }
    }

    fun getUser(query: String?) {
        coroutineScope.launch {
            CodewarsApi.retrofitService.getUser(query)
        }
    }

    private suspend fun insert(user: DatabaseUser) {
        withContext(Dispatchers.IO) {
            database.userDatabaseDao.insert(user)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}