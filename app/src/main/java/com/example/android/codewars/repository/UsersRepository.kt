package com.example.android.codewars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.codewars.database.UsersDao
import com.example.android.codewars.database.asDomainModel
import com.example.android.codewars.domainModels.User
import com.example.android.codewars.network.ApiService
import com.example.android.codewars.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDao: UsersDao,
    private val apiService: ApiService
) {

    val users: LiveData<List<User>> = Transformations.map(usersDao.getAllUsers()) {
        it.asDomainModel().asReversed()
    }

    suspend fun getUser(username: String?) {
        withContext(Dispatchers.IO) {
            refreshUser(username)
            usersDao.getUser(username)
        }
    }

    private suspend fun refreshUser(username: String?) {
        withContext(Dispatchers.IO) {
            val user = apiService.getUser(username)
            usersDao.insert(user.asDatabaseModel())
        }
    }
}