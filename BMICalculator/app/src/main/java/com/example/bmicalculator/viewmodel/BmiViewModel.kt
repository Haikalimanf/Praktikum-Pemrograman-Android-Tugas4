package com.example.bmicalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmicalculator.repository.BMIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val bmiRepository: BMIRepository
): ViewModel() {
    private val _bmiResult = MutableLiveData<String>()
    val bmiResult: LiveData<String> = _bmiResult

    fun calculateBMI(weight: Double, height: Double): Double {
        val bmi = bmiRepository.calculateBMI(weight, height)
        _bmiResult.value = bmi.toString()
        return bmi
    }
}