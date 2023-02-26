package com.example.studyproject.coroutines.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel : ViewModel() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Main.immediate)

    fun doWork() {
        var number = 1
        //This job will start, but it will not do full work CoroutineContext.cancelChildren()
        scope.launch {
            Log.d("TestTag", "hardWork $number before thread=${Thread.currentThread().id}")
            hardWork(number)
        }
        number ++
        //This job will start, but it will not do full work due to CoroutineContext.cancelChildren()
        scope.launch {
            Log.d("TestTag", "hardWork $number before thread=${Thread.currentThread().id}")
            hardWork(number)
        }
        number++
        Thread.sleep(500)
        scope.coroutineContext.cancelChildren()
        // This job will start, but it will not do full work due to Job.cancelChildren()
        scope.launch {
            Log.d("TestTag", "hardWork $number before thread=${Thread.currentThread().id}")
            hardWork(number)
        }
        number++
        Thread.sleep(500)
        job.cancelChildren()
        scope.launch {
            Log.d("TestTag", "hardWork $number before thread=${Thread.currentThread().id}")
            hardWork(number)
        }
        number++
        Thread.sleep(3700)
        job.cancel()
        // This job will not start, because job is canceled. More over, block in launch will not be invoked
        scope.launch {
            Log.d("TestTag", "hardWork try to launch coroutine via canceled job")
            Log.d("TestTag", "hardWork $number before thread=${Thread.currentThread().id}")
            hardWork(number)
        }
        Log.d("TestTag", "hardWork $number finish thread=${Thread.currentThread().id}")
    }

    private suspend fun hardWork(jobNumber: Int) =
        withContext(Dispatchers.IO) {
            Log.d("TestTag", "hardWork $jobNumber start thread=${Thread.currentThread().id}")
            Thread.sleep(2000)
            Log.d("TestTag", "hardWork $jobNumber continue thread=${Thread.currentThread().id}")
            delay(1000)
            Log.d("TestTag", "hardWork $jobNumber finish thread=${Thread.currentThread().id}")
        }
}