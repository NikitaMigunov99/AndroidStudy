package com.example.studyproject.coroutines.repo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Implementation of [FibonacciNumbersRepository]
 */
class FibonacciNumbersRepositoryImpl(private val dispatcher: CoroutineDispatcher) : FibonacciNumbersRepository {

    override suspend fun getFirstNumbers(numbersAmount: Int): List<Long> =
        withContext(dispatcher) {
            computeNumbers(numbersAmount)
        }

    override suspend fun getNumbers(numbersAmount: Int, firstNumber: Long, secondNumber: Long): List<Long> =
        withContext(dispatcher) {
            computeNumbers(numbersAmount, firstNumber, secondNumber)
        }

    private fun computeNumbers(numbersAmount: Int, firstNumber: Long = 0, secondNumber: Long = 1): List<Long> {
        if (numbersAmount == 0) return emptyList()
        val numbers = mutableListOf<Long>()
        if (firstNumber == 0L) {
            numbers.add(firstNumber)
            numbers.add(secondNumber)
        }
        var a = firstNumber
        var b = secondNumber
        var c: Long
        for (i in 2..numbersAmount) {
            c = a + b
            numbers.add(c)
            a = b
            b = c
        }
        return numbers
    }
}