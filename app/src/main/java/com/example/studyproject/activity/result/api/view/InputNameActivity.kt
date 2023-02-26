package com.example.studyproject.activity.result.api.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.studyproject.R
import com.example.studyproject.activity.result.api.models.InputErrorModel
import com.example.studyproject.activity.result.api.models.PersonalDataModel
import com.example.studyproject.activity.result.api.view.ResultMainActivity.Companion.FULL_DATA
import com.example.studyproject.activity.result.api.viewmodel.InputNameViewModel
import com.example.studyproject.activity.result.api.viewmodel.InputNameViewModel.InputFieldType
import com.google.android.material.textfield.TextInputLayout

class InputNameActivity : AppCompatActivity() {

    private val viewModel: InputNameViewModel by viewModels()
    private lateinit var firstNameTextFiled: TextInputLayout
    private lateinit var secondNameTextFiled: TextInputLayout
    private lateinit var ageTextFiled: TextInputLayout
    private lateinit var phoneNumberTextFiled: TextInputLayout
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_name_activity)

        initView(intent.getBooleanExtra(FULL_DATA, false))
        initObservers()
    }

    private fun initView(isFullData: Boolean) {
        firstNameTextFiled = findViewById(R.id.first_name)
        secondNameTextFiled = findViewById(R.id.second_name)
        ageTextFiled = findViewById(R.id.age)
        phoneNumberTextFiled = findViewById(R.id.phone_number)

        firstNameTextFiled.editText?.doAfterTextChanged {
            it?.let { viewModel.setName(it.toString()) }
        }
        secondNameTextFiled.editText?.doAfterTextChanged {
            it?.let { viewModel.setSecondName(it.toString()) }
        }
        if (isFullData) {
            ageTextFiled.editText?.doAfterTextChanged {
                it?.let { viewModel.setAge(it.toString()) }
            }
            phoneNumberTextFiled.editText?.doAfterTextChanged {
                it?.let { viewModel.setPhoneNumber(it.toString()) }
            }
        } else {
            ageTextFiled.visibility = View.GONE
            phoneNumberTextFiled.visibility = View.GONE
        }

        finishButton = findViewById(R.id.finish_button)
        finishButton.isClickable = false
        finishButton.isEnabled = false
        finishButton.setOnClickListener { viewModel.finishButtonClick() }
    }

    private fun initObservers() {
        viewModel.error.observe(this) { setOrClearInputError(it) }
        viewModel.data.observe(this) { setDataAndFinish(it) }
        viewModel.finishButtonClickable.observe(this) { setFinishButtonClickable(it) }
    }

    private fun setOrClearInputError(errorModel: InputErrorModel) {
        when (errorModel.inputFiled) {
            InputFieldType.FIRST_NAME.value -> firstNameTextFiled.error =
                if (errorModel.isError) getString(R.string.input_error) else null
            InputFieldType.SECOND_NAME.value -> secondNameTextFiled.error =
                if (errorModel.isError) getString(R.string.input_error) else null
        }
    }

    private fun setDataAndFinish(data: PersonalDataModel) {
        val intent = Intent()
        intent.putExtra(PERSONAL_DATA, data)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setFinishButtonClickable(isClickable: Boolean) {
        finishButton.isClickable = isClickable
        finishButton.isEnabled = isClickable
    }

    companion object {
        const val PERSONAL_DATA = "personalData"
    }
}