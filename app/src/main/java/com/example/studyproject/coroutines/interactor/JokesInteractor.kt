package com.example.studyproject.coroutines.interactor

import com.example.studyproject.coroutines.models.JokeModel

interface JokesInteractor {
    suspend fun getJokes(): List<JokeModel>
}