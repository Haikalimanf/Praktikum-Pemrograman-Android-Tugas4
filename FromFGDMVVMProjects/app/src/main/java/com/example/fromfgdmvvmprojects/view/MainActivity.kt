package com.example.fromfgdmvvmprojects.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fromfgdmvvmprojects.R
import com.example.fromfgdmvvmprojects.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InputFragment())
            .commit()

    }
}