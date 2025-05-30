package com.example.activitylifecycleprojects

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitylifecycleprojects.MainActivity.Companion

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d(TAG, "onCreate Terpanggil: ")

    }

    fun LaunchActivity3(view: View) {
        startActivity(Intent(this, MainActivity3::class.java))
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