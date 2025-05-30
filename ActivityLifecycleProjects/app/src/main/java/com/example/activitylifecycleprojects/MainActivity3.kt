package com.example.activitylifecycleprojects

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        Log.d(TAG, "onCreate Terpanggil: ")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume Terpanggil: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Terpanggil: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Terpanggil: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop terpanggil: ")
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}