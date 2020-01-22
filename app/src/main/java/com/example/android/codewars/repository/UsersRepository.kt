package com.example.android.codewars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.codewars.database.UserDatabase
import com.example.android.codewars.models.User
import com.example.android.codewars.network.CodewarsApi
import com.example.android.codewars.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val database: UserDatabase) {

    val users: LiveData<List<User>> = Transformations.map(database.userDatabaseDao.getAllUsers()) {
        it.asReversed()
    }

    suspend fun getUser(query: String) {
        withContext(Dispatchers.IO) {
            val user = CodewarsApi.retrofitService.getUser(query)
            database.userDatabaseDao.insert(user.asDatabaseModel())
        }
    }
}