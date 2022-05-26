package com.example.studyproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyproject.R
import com.example.studyproject.di.DaggerMainComponent
import com.example.studyproject.viewmodel.FibonacciNumbersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FibonacciNumbersViewModel
    private val adapter = FibonacciNumbersAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val daggerMainComponent = DaggerMainComponent.builder().build()
        viewModel = FibonacciNumbersViewModel(daggerMainComponent.getFibonacciNumbersInteractor())
        initRecyclerView()

        viewModel.numbers.observe(this) { adapter.setData(it) }
        viewModel.getFirstNumbers()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.numbers)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItems = layoutManager.findLastVisibleItemPosition()

                    if (viewModel.isComputing.value == false) {
                        if ((visibleItemCount + lastVisibleItems) > totalItemCount) {
                            viewModel.getNumbers()
                        }
                    }
                }
            }
        })
    }
}