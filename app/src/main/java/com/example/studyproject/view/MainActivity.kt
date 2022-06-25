package com.example.studyproject.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findViewById<Button>(R.id.button_android_site).setOnClickListener {
            val uri: Uri = Uri.parse("https://developer.android.com/training/app-links/deep-linking");
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = uri
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_deeplink).setOnClickListener {
            val uri: Uri = Uri.parse("study-activity://?name=Antonio");
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = uri
            }
            startActivity(intent)
        }
    }
}