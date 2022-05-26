package com.example.studyproject.di

import com.example.studyproject.interactor.FibonacciNumbersInteractor
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {

    fun getFibonacciNumbersInteractor() : FibonacciNumbersInteractor
}