package com.example.studyproject.di

import com.example.studyproject.interactor.FibonacciNumbersInteractor
import com.example.studyproject.interactor.FibonacciNumbersInteractorImpl
import com.example.studyproject.repo.FibonacciNumbersRepository
import com.example.studyproject.repo.FibonacciNumbersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideFibonacciNumbersRepository(): FibonacciNumbersRepository =
        FibonacciNumbersRepositoryImpl()

    @Provides
    fun provideFibonacciNumbersInteractor(repository: FibonacciNumbersRepository): FibonacciNumbersInteractor =
        FibonacciNumbersInteractorImpl(repository)
}