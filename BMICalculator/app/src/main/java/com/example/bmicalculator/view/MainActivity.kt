package com.example.bmicalculator.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.example.bmicalculator.model.User
import com.example.bmicalculator.viewmodel.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BmiViewModel by viewModels()
    private var resultUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener {
            val berat = binding.edtBerat.text.toString().toDoubleOrNull()
            val tinggi = binding.edtHeight.text.toString().toDoubleOrNull()

            if (berat == null || tinggi == null) {
                Toast.makeText(this, "Semua field (Berat, Tinggi) harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = viewModel.calculateBMI(berat, tinggi)
            resultUser = User(result)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("USER_RESULT",resultUser)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}