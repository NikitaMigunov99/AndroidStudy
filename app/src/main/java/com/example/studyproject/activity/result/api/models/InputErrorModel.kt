package com.example.studyproject.activity.result.api.models

/**
 * @property inputFiled type/identifier of input filed
 * @property isError is input error
 */
data class InputErrorModel(
    val inputFiled: String,
    val isError: Boolean
)