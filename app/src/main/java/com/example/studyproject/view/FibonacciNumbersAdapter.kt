package com.example.studyproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyproject.R

/**
 * [RecyclerView.Adapter] for Fibonacci numbers list
 */
class FibonacciNumbersAdapter : RecyclerView.Adapter<FibonacciNumbersAdapter.FibonacciNumbers>() {

    private val numbers = mutableListOf<Long>()

    fun setData(data: List<Long>) {
        val size = numbers.size
        numbers.addAll(data)
        notifyItemRangeInserted(size, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibonacciNumbers {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fibonacci_number_item, parent, false)
        return FibonacciNumbers(view)
    }

    override fun onBindViewHolder(holder: FibonacciNumbers, position: Int) {
        holder.bind(numbers[position])
    }

    override fun getItemCount(): Int = numbers.size

    inner class FibonacciNumbers(view: View) : RecyclerView.ViewHolder(view) {
        private val numberText: TextView = view.findViewById(R.id.number_text_view)
        fun bind(number: Long) {
            numberText.text = number.toString()
        }
    }
}