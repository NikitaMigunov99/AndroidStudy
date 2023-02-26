package com.example.studyproject.coroutines.di

import com.example.studyproject.coroutines.interactor.FibonacciNumbersInteractor
import com.example.studyproject.coroutines.interactor.JokesInteractor
import dagger.Component

@Component(modules = [MainModule::class, NetworkModule::class])
interface MainComponent {

    fun getFibonacciNumbersInteractor(): FibonacciNumbersInteractor

    fun getJokesInteractor(): JokesInteractor
}