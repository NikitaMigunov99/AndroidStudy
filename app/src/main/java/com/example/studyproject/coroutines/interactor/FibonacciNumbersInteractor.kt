package com.example.studyproject.coroutines.interactor

/**
 * Interactor for Fibonacci numbers obtaining
 */
interface FibonacciNumbersInteractor {

    /**
     * Obtaining first [numbersAmount] Fibonacci numbers
     */
    suspend fun getFirstNumbers(numbersAmount: Int): List<Long>

    /**
     * Obtaining Fibonacci numbers
     */
    suspend fun getNumbers(numbersAmount: Int, firstNumber: Long, secondNumber: Long): List<Long>
}