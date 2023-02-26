package com.example.studyproject.common

fun interface Converter<T, R> {
    fun convert(value: T): R
}