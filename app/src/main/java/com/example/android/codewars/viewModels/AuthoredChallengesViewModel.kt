package com.example.android.codewars.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.codewars.models.AuthoredChallenge
import com.example.android.codewars.network.ApiStatus
import com.example.android.codewars.network.CodewarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthoredChallengesViewModel(application: Application, username: String) :
    AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _authoredChallenges = MutableLiveData<List<AuthoredChallenge>>()
    val authoredChallenges: LiveData<List<AuthoredChallenge>>
        get() = _authoredChallenges

    init {
        coroutineScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _authoredChallenges.value = CodewarsApi.retrofitService.getAutheredChallenges(username).data
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}