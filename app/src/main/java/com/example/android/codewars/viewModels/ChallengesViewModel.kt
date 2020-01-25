package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.codewars.models.Challenge
import com.example.android.codewars.network.ApiStatus
import com.example.android.codewars.network.CodewarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChallengesViewModel(application: Application, username: String) :
    AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _challenges = MutableLiveData<List<Challenge>>()
    val challenges: LiveData<List<Challenge>>
        get() = _challenges

    init {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _challenges.value = CodewarsApi.retrofitService.getAutheredChallenges(username).data
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}