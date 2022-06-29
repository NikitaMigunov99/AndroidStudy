package com.example.studyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studyproject.models.InputErrorModel
import com.example.studyproject.models.PersonalDataModel

class InputNameViewModel : ViewModel() {

    val error: LiveData<InputErrorModel>
        get() = _error
    val finishButtonClickable: LiveData<Boolean>
        get() = _finishButtonClickable
    val data: LiveData<PersonalDataModel>
        get() = _data

    private val _error = MutableLiveData<InputErrorModel>()
    private val _finishButtonClickable = MutableLiveData(false)
    private val _data = MutableLiveData<PersonalDataModel>()
    private val _currentData = MutableLiveData(PersonalDataModel("", ""))

    fun setName(name: String) {
        val human = _currentData.value
        _finishButtonClickable.value =
            isValidName(name, InputFieldType.FIRST_NAME) && !human?.secondName.isNullOrEmpty()
        human?.name = name
        _currentData.value = human
    }

    fun setSecondName(secondName: String) {
        val human = _currentData.value
        _finishButtonClickable.value =
            isValidName(secondName, InputFieldType.SECOND_NAME) && !human?.name.isNullOrEmpty()
        human?.secondName = secondName
        _currentData.value = human
    }

    fun setAge(age: String) {
        try {
            _currentData.value?.age = age.toInt()
        } catch (e: NumberFormatException) {
            _currentData.value?.age = 0
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        _currentData.value?.phoneNumber = phoneNumber
    }

    private fun isValidName(name: String, fieldType: InputFieldType): Boolean {
        val isError = pattern.containsMatchIn(name) || name.isEmpty()
        _error.value = InputErrorModel(fieldType.toString(), isError)
        return !isError
    }

    fun finishButtonClick() {
        _currentData.value?.let {
            _data.value = it
        }
    }

    enum class InputFieldType(val value: String) {
        FIRST_NAME("FIRST_NAME"),
        SECOND_NAME("SECOND_NAME")
    }

    companion object {
        private const val REGULAR_PATTERN = "[0-9&*#%@]"
        private val pattern = REGULAR_PATTERN.toRegex()
    }
}