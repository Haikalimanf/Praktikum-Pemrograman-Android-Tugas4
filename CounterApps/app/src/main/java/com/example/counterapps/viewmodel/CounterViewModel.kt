package com.example.counterapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.counterapps.repository.CounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CounterViewModel @Inject constructor(
    private val counterRepository: CounterRepository
) : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    fun incrementCounter() {
        val newValue = counterRepository.incrementCounter(_counter.value ?: 0)
        _counter.value = newValue
    }

    fun decrementCounter() {
        val newValue = counterRepository.decrementCounter(_counter.value ?: 0)
        _counter.value = newValue
    }
}