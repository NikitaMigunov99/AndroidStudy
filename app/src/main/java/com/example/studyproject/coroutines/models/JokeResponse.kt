package com.example.studyproject.coroutines.models

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("setup")
    val setup: String,
    @SerializedName("punchline")
    val punchline: String
)