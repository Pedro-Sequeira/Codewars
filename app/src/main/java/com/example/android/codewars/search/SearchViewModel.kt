package com.example.android.codewars.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.codewars.models.User
import com.example.android.codewars.network.CodewarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUser()
    }

    private fun getUser() {
        coroutineScope.launch {
            val getUserDeferred = CodewarsApi.retrofitService.getUser()
            try {
                val result = getUserDeferred.await()
                _user.value = result
            } catch (e: Exception) {

            }
        }
    }
}