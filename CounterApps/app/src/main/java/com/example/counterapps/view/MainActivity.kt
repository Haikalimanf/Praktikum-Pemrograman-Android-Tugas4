package com.example.counterapps.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.counterapps.databinding.ActivityMainBinding
import com.example.counterapps.viewmodel.CounterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterViewModel.counter.observe(this) { counter ->
            binding.txtViewCounter.text = counter.toString()
        }

        binding.btnIncrement.setOnClickListener {
            counterViewModel.incrementCounter()
        }

        binding.btnDecrement.setOnClickListener {
            counterViewModel.decrementCounter()
        }

    }
}