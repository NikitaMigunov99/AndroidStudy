package com.example.studyproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyproject.interactor.FibonacciNumbersInteractor
import kotlinx.coroutines.launch

/**
 * ViewModel for Fibonacci numbers
 *
 * @param interactor interactor for Fibonacci numbers obtaining
 */
class FibonacciNumbersViewModel(private val interactor: FibonacciNumbersInteractor) : ViewModel() {

    val numbers: LiveData<List<Long>>
        get() = _numbers
    val isComputing: LiveData<Boolean>
        get() = _isComputing

    private val _numbers = MutableLiveData<List<Long>>()
    private val _isComputing = MutableLiveData<Boolean>()

    fun getFirstNumbers() {
        viewModelScope.launch {
            val values = try {
                interactor.getFirstNumbers(FIRST_NUMBERS_AMOUNT)
            } catch (e: Exception) {
                Log.e(FibonacciNumbersViewModel::class.simpleName, e.toString())
                emptyList()
            }
            if (values.isNotEmpty()) {
                _numbers.postValue(values)
            }
            _isComputing.postValue(false)
        }
    }

    fun getNumbers() {
        val numbers = _numbers.value.orEmpty()
        if (numbers.isNotEmpty()) {
            _isComputing.value = true
            viewModelScope.launch {
                val newValues = try {
                    interactor.getNumbers(NUMBERS_AMOUNT, numbers[numbers.size - 2], numbers.last())
                } catch (e: Exception) {
                    Log.e(FibonacciNumbersViewModel::class.simpleName, e.toString())
                    emptyList()
                }
                if (newValues.isNotEmpty()) {
                    _numbers.postValue(newValues)
                }
                _isComputing.postValue(false)
            }
        }
    }

    companion object {
        private const val FIRST_NUMBERS_AMOUNT = 50
        private const val NUMBERS_AMOUNT = 30
    }
}