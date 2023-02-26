package com.example.studyproject.activity.result.api.contracts

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.studyproject.activity.result.api.models.PersonalDataModel
import com.example.studyproject.activity.result.api.view.InputNameActivity
import com.example.studyproject.activity.result.api.view.InputNameActivity.Companion.PERSONAL_DATA
import com.example.studyproject.activity.result.api.view.ResultMainActivity.Companion.FULL_DATA

class PersonalDataResultContract : ActivityResultContract<Boolean, PersonalDataModel?>() {

    override fun createIntent(context: Context, input: Boolean): Intent =
        Intent(context, InputNameActivity::class.java).putExtra(FULL_DATA, input)

    override fun parseResult(resultCode: Int, intent: Intent?): PersonalDataModel? =
        intent?.getSerializableExtra(PERSONAL_DATA) as? PersonalDataModel
}