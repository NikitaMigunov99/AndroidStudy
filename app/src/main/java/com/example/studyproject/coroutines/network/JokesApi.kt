package com.example.studyproject.coroutines.network

import com.example.studyproject.coroutines.models.JokeResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {

    @GET("/random_ten")
    suspend fun getTenJokes(): Response<List<JokeResponse>?>
}