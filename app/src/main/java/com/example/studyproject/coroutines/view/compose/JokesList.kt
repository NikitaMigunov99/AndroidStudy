package com.example.studyproject.coroutines.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.studyproject.coroutines.models.JokeModel

@Preview
@Composable
fun JokesList(@PreviewParameter(JokesProvider::class) jokes: List<JokeModel>) {
    val marginBetweenChildren = 16.dp
    LazyColumn(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = marginBetweenChildren,
            alignment = Alignment.CenterVertically
        )
    ) {
        items(jokes) { joke ->
            Column(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp
                )
            ) {
                Text(text = joke.introduction)
                Text(text = joke.joke)
            }
        }
    }
}

class JokesProvider : PreviewParameterProvider<JokeModel> {
    override val values = sequenceOf(
        JokeModel(introduction = "I know people good living without it", joke = "Without brains"),
        JokeModel(introduction = "Miser pays twice", joke = "Sometimes thrice")
    )
}
