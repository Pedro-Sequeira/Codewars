package com.example.android.codewars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.codewars.models.User
import com.example.android.codewars.network.ApiService
import com.example.android.codewars.network.ApiStatus
import com.example.android.codewars.network.asDatabaseModel
import com.example.android.codewars.repository.database.UserDB
import com.example.android.codewars.repository.database.UsersDao
import com.example.android.codewars.repository.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val usersDao: UsersDao,
    private val apiService: ApiService
) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    val users: LiveData<List<User>> = Transformations.map(usersDao.getAllUsers()) {
        it.asDomainModel()
    }

    suspend fun fetchUser(username: String?) {
        withContext(Dispatchers.IO) {
            usersDao.getUser(username)
            refreshUser(username)
        }
    }

    private suspend fun refreshUser(username: String?) {
        withContext(Dispatchers.Main) {
            try {
                _status.value = ApiStatus.LOADING
                val result = apiService.getUser(username)
                if (result.success == null) {   // success only shows on failure
                    _status.value = ApiStatus.DONE
                    insertUser(result.asDatabaseModel())
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private suspend fun insertUser(userDB: UserDB) {
        withContext(Dispatchers.IO) {
            usersDao.insert(userDB)
        }
    }
}