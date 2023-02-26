package com.example.studyproject.coroutines.di

import com.example.studyproject.common.Converter
import com.example.studyproject.coroutines.converter.JokesConverter
import com.example.studyproject.coroutines.interactor.FibonacciNumbersInteractor
import com.example.studyproject.coroutines.interactor.FibonacciNumbersInteractorImpl
import com.example.studyproject.coroutines.interactor.JokesInteractor
import com.example.studyproject.coroutines.interactor.JokesInteractorImpl
import com.example.studyproject.coroutines.models.JokeModel
import com.example.studyproject.coroutines.models.JokeResponse
import com.example.studyproject.coroutines.network.JokesApi
import com.example.studyproject.coroutines.repo.FibonacciNumbersRepository
import com.example.studyproject.coroutines.repo.FibonacciNumbersRepositoryImpl
import com.example.studyproject.coroutines.repo.JokesRepository
import com.example.studyproject.coroutines.repo.JokesRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class MainModule {

    @Provides
    fun provideFibonacciNumbersRepository(@Named(DEFAULT) dispatcher: CoroutineDispatcher): FibonacciNumbersRepository =
        FibonacciNumbersRepositoryImpl(dispatcher)

    @Provides
    fun provideFibonacciNumbersInteractor(repository: FibonacciNumbersRepository): FibonacciNumbersInteractor =
        FibonacciNumbersInteractorImpl(repository)

    @Provides
    fun provideJokesRepository(
        api: JokesApi,
        converter: Converter<List<JokeResponse>, List<JokeModel>>,
        @Named(IO) dispatcher: CoroutineDispatcher
    ): JokesRepository = JokesRepositoryImpl(api, converter, dispatcher)

    @Provides
    fun provideJokesInteractor(repository: JokesRepository): JokesInteractor = JokesInteractorImpl(repository)

    @Provides
    fun provideJokesConverter(): Converter<List<JokeResponse>, List<JokeModel>> = JokesConverter()

    @Provides
    @Named(DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Named(IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    private companion object {
        const val DEFAULT = "DEFAULT"
        const val IO = "IO"
    }
}