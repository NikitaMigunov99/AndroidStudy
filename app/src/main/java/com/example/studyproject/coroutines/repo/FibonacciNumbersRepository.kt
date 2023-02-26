package com.example.studyproject.coroutines.repo

/**
 * Repository for Fibonacci numbers obtaining
 */
interface FibonacciNumbersRepository {

    /**
     * Obtaining first [numbersAmount] Fibonacci numbers
     */
    suspend fun getFirstNumbers(numbersAmount: Int): List<Long>

    /**
     * Obtaining Fibonacci numbers
     */
    suspend fun getNumbers(numbersAmount: Int, firstNumber: Long, secondNumber: Long): List<Long>
}