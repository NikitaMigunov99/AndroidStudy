package com.example.studyproject.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R

class FakeFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fake_first_activity)
        val intent = Intent(this, FakeSecondActivity::class.java)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener { startActivity(intent) }
    }
}