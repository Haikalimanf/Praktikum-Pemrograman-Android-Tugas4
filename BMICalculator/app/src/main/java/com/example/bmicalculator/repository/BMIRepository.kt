package com.example.bmicalculator.repository


class BMIRepository {

    fun calculateBMI(weight: Double, height: Double): Double {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

}