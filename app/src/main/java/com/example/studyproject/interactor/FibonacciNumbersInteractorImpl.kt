package com.example.studyproject.interactor

import com.example.studyproject.repo.FibonacciNumbersRepository

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