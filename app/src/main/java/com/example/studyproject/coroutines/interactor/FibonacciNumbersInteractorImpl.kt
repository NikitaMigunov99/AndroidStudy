package com.example.studyproject.coroutines.interactor

import com.example.studyproject.coroutines.repo.FibonacciNumbersRepository

/**
 * Implementation of [FibonacciNumbersInteractor]
 *
 * @param repository Repository for Fibonacci numbers obtaining
 */
class FibonacciNumbersInteractorImpl(private val repository: FibonacciNumbersRepository) : FibonacciNumbersInteractor {

    override suspend fun getFirstNumbers(numbersAmount: Int): List<Long> =
        repository.getFirstNumbers(numbersAmount)

    override suspend fun getNumbers(numbersAmount: Int, firstNumber: Long, secondNumber: Long): List<Long> =
        repository.getNumbers(numbersAmount, firstNumber, secondNumber)
}