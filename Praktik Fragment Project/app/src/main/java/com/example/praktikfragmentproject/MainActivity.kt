package com.example.praktikfragmentproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikfragmentproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentA = FragmentA()
        val fragmentB = FragmentB()

        fragmentManager.beginTransaction()
            .add(R.id.framelayout, fragmentA, FragmentA::class.java.simpleName)
            .commit()


        binding.btnA.setOnClickListener {
            var fragment = fragmentManager.findFragmentByTag(FragmentA::class.java.simpleName)
            if (fragment !is FragmentA){
                fragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout, fragmentA, FragmentA::class.java.simpleName)
                    .commit()
            }
        }

        binding.btnB.setOnClickListener {
            var fragment = fragmentManager.findFragmentByTag(FragmentB::class.java.simpleName)
            if (fragment !is FragmentB){
                fragmentManager
                    .beginTransaction()
                    .replace(R.id.framelayout, fragmentB, FragmentB::class.java.simpleName)
                    .commit()
            }
        }
    }
}