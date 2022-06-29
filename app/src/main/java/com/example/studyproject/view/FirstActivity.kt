package com.example.studyproject.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R
import com.example.studyproject.models.PersonalDataModel

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity)
        findViewById<Button>(R.id.button_one).setOnClickListener {
            val intent = Intent()
            intent.putExtra(FIRST_ACTIVITY_DATA, PersonalDataModel("Anna", "Federer"))
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val FIRST_ACTIVITY_DATA = "firstActivityData"
    }
}