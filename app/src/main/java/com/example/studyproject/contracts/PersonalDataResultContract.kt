package com.example.studyproject.contracts

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.studyproject.models.PersonalDataModel
import com.example.studyproject.view.InputNameActivity
import com.example.studyproject.view.InputNameActivity.Companion.PERSONAL_DATA
import com.example.studyproject.view.MainActivity.Companion.FULL_DATA

class PersonalDataResultContract : ActivityResultContract<Boolean, PersonalDataModel?>() {

    override fun createIntent(context: Context, input: Boolean): Intent =
        Intent(context, InputNameActivity::class.java).putExtra(FULL_DATA, input)

    override fun parseResult(resultCode: Int, intent: Intent?): PersonalDataModel? =
        intent?.getSerializableExtra(PERSONAL_DATA) as? PersonalDataModel
}