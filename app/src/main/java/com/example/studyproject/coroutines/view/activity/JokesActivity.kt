package com.example.studyproject.coroutines.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.studyproject.R
import com.example.studyproject.common.ViewModelFactory
import com.example.studyproject.coroutines.di.DaggerMainComponent
import com.example.studyproject.coroutines.models.JokeModel
import com.example.studyproject.coroutines.view.compose.JokesList
import com.example.studyproject.coroutines.viewmodel.JokesViewModel

class JokesActivity : AppCompatActivity() {

    private lateinit var viewModel: JokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.jokesLiveData.observe(this) { createContent(it) }
        viewModel.errorLiveData.observe(this) { showToast() }
        viewModel.getTenJokes()
    }

    private fun createContent(jokes: List<JokeModel>) = setContent { JokesList(jokes = jokes) }

    private fun showToast() {
        val toast = Toast.makeText(this, getText(R.string.jokes_not_loaded), Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun getViewModel(): JokesViewModel {
        val interactor = DaggerMainComponent.builder().build().getJokesInteractor()
        val provider = ViewModelProvider(this, ViewModelFactory { JokesViewModel(interactor) })
        return provider[JokesViewModel::class.java]
    }
}