package com.example.studyproject.coroutines.repo

import com.example.studyproject.common.Converter
import com.example.studyproject.coroutines.models.JokeModel
import com.example.studyproject.coroutines.models.JokeResponse
import com.example.studyproject.coroutines.network.JokesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

class JokesRepositoryImpl(
    private val jokesApi: JokesApi,
    private val converter: Converter<List<JokeResponse>, List<JokeModel>>,
    private val dispatcher: CoroutineDispatcher
) : JokesRepository {

    override suspend fun getJokes(): List<JokeModel> =
        withContext(dispatcher) {
            val response = jokesApi.getTenJokes()
            if (!response.isSuccessful) {
                onError(response)
            }
            val jokes = response.body() ?: emptyList()
            if (jokes.isEmpty()) {
                throw IOException("Jokes list is empty")
            } else {
                converter.convert(jokes)
            }
        }

    private fun onError(response: Response<List<JokeResponse>?>) {
        val errMsg = response.errorBody()?.string()?.let {
            JSONObject(it).getString("error") // or whatever your message is
        } ?: run {
            response.code().toString()
        }
        throw IOException("Could not load jokes \nmessage: $errMsg")
    }
}