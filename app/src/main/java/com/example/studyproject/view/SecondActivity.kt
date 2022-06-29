package com.example.studyproject.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R
import com.example.studyproject.models.PersonalDataModel

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        findViewById<Button>(R.id.button_second).setOnClickListener {
            val intent = Intent()
            intent.putExtra(SECOND_ACTIVITY_DATA, PersonalDataModel("Boris", "Biden"))
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val SECOND_ACTIVITY_DATA = "secondActivityData"
    }
}