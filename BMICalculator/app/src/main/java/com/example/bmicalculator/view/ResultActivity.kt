package com.example.bmicalculator.view

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.R
import com.example.bmicalculator.databinding.ActivityResultBinding
import com.example.bmicalculator.model.User
import com.example.bmicalculator.viewmodel.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("USER_RESULT", User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<User>("USER_RESULT")
        }

        user?.let { dataUser ->
            val kategori = when {
                dataUser.bmi < 18.5 -> "Berat Badan Kurang"
                dataUser.bmi in 18.5..24.9 -> "Normal"
                dataUser.bmi in 25.0..29.9 -> "Kelebihan Berat Badan"
                else -> "Obesitas"
            }
            binding.resultBmi.text = getString(R.string.result_bmi, dataUser.bmi)

            binding.resultKategori.text = getString(R.string.result_kategori, kategori)
        }

        binding.btnToInfo.setOnClickListener {
            val bottomSheet = InfoDeveloper()
            bottomSheet.show(supportFragmentManager, "InfoDeveloper")
        }
    }
}