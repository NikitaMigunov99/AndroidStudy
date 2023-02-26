package com.example.studyproject

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyproject.activity.result.api.view.ResultMainActivity
import com.example.studyproject.coroutines.view.activity.CoroutinesStartActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent { CreateContent() }
    }

    @Composable
    private fun CreateContent() {
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
            Button(onClick = { startActivity(Intent(this@MainActivity, CoroutinesStartActivity::class.java)) }) {
                Text(stringResource(R.string.coroutines), fontSize = 25.sp)
            }
            Button(onClick = { startActivity(Intent(this@MainActivity, ResultMainActivity::class.java)) }) {
                Text(stringResource(R.string.result_activity), fontSize = 25.sp)
            }
        }
    }
}