package com.example.studyproject.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.deep_link_activity)
        val textView = findViewById<TextView>(R.id.text)

        val uri = intent?.data
        uri?.let {
            textView.text = it.getQueryParameter(NAME).orEmpty()
        }
    }

    companion object {
        private const val NAME = "name"
    }
}