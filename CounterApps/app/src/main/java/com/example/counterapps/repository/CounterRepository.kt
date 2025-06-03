package com.example.counterapps.repository

class CounterRepository {
    fun incrementCounter(current: Int): Int {
        return current+1
    }

    fun decrementCounter(current: Int): Int {
        return current-1
    }
}