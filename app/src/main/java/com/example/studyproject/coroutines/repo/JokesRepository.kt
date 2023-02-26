package com.example.studyproject.coroutines.repo

import com.example.studyproject.coroutines.models.JokeModel

interface JokesRepository {

    suspend fun getJokes(): List<JokeModel>
}