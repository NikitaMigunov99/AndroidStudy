package com.example.studyproject.coroutines.view.activity

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studyproject.coroutines.viewmodel.CoroutinesViewModel

class CoroutinesActivity : AppCompatActivity() {

    private val viewModel: CoroutinesViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.doWork()
    }
}