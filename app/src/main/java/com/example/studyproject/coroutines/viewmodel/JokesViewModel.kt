package com.example.studyproject.coroutines.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyproject.coroutines.interactor.JokesInteractor
import com.example.studyproject.coroutines.models.JokeModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class JokesViewModel(private val jokesInteractor: JokesInteractor) : ViewModel() {

    val jokesLiveData: LiveData<List<JokeModel>>
        get() = _jokesLiveData

    val errorLiveData: LiveData<Unit>
        get() = _errorLiveData

    private val _jokesLiveData = MutableLiveData<List<JokeModel>>()
    private val _errorLiveData = MutableLiveData<Unit>()

    fun getTenJokes() =
        viewModelScope.launch {
            runCatching { jokesInteractor.getJokes() }
                .onSuccess { _jokesLiveData.value = it }
                .onFailure { onError(it) }
        }

    private fun onError(e: Throwable) {
        Log.e("JokesViewModel", e.toString())
        if (e is CancellationException) {
            throw e
        } else {
            _errorLiveData.value = Unit
        }
    }
}