package com.example.loginlogoutprojects

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginlogoutprojects.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var dataStoreManager: DataStoreManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FUNGSI SLIDE
        val slide = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.slide_in)
        binding.txtLoading.startAnimation(slide)
        binding.imgLogo.startAnimation(slide)
        binding.txtNamaAplikasi.startAnimation(slide)

        dataStoreManager = DataStoreManager(this)


        lifecycleScope.launch {
            delay(3000)
            val user = dataStoreManager.userDataFlow.first()
            if (user != null){
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }


    }
}