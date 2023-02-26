package com.example.studyproject.coroutines.interactor

import com.example.studyproject.coroutines.models.JokeModel
import com.example.studyproject.coroutines.repo.JokesRepository

class JokesInteractorImpl(private val repository: JokesRepository) : JokesInteractor {

    override suspend fun getJokes(): List<JokeModel> = repository.getJokes()
}