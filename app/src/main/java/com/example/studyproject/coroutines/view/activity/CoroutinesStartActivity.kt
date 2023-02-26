package com.example.studyproject.coroutines.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyproject.R

class CoroutinesStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CreateContent() }
    }

    @Preview
    @Composable
    fun CreateContent() {
        val marginBetweenChildren = 36.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = marginBetweenChildren,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { startActivity(Intent(this@CoroutinesStartActivity, JokesActivity::class.java)) }) {
                Text(stringResource(R.string.jokes), fontSize = 25.sp, textAlign = TextAlign.Center)
            }
            Button(onClick = { startActivity(Intent(this@CoroutinesStartActivity, FibonacciActivity::class.java)) }) {
                Text(stringResource(R.string.fibonacci_numbers), fontSize = 25.sp, textAlign = TextAlign.Center)
            }
            Button(onClick = { startActivity(Intent(this@CoroutinesStartActivity, CoroutinesActivity::class.java)) }) {
                Text(stringResource(R.string.coroutines_experiments), fontSize = 25.sp, textAlign = TextAlign.Center)
            }
        }
    }
}