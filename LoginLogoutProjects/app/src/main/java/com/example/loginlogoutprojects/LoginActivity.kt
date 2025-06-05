package com.example.loginlogoutprojects

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.loginlogoutprojects.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val nama = binding.edtNama.text.toString()
            val email = binding.edtEmail.text.toString()
            val noHp = binding.edtNoHp.text.toString()

            if (nama.isEmpty()){
                binding.edtNama.error = "Nama Tidak Boleh Kosong"
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.edtEmail.error = "Email Tidak Boleh Kosong"
                return@setOnClickListener
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmail.error = "Email Tidak Valid"
                return@setOnClickListener
            }

            if (noHp.isEmpty()){
                binding.edtNoHp.error = "No Hp Tidak Boleh Kosong"
                return@setOnClickListener
            }

            dataStoreManager = DataStoreManager(this)

            lifecycleScope.launch {
                dataStoreManager.saveUserData(nama,email,noHp)
                navigateToMain()
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}