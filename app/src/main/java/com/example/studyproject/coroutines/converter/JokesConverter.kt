package com.example.studyproject.coroutines.converter

import com.example.studyproject.common.Converter
import com.example.studyproject.coroutines.models.JokeResponse
import com.example.studyproject.coroutines.models.JokeModel

class JokesConverter : Converter<List<JokeResponse>, List<JokeModel>> {
    override fun convert(value: List<JokeResponse>) = value.map {
        JokeModel(introduction = it.setup, joke = it.punchline)
    }
}