package com.example.suweorajamuapps.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suweorajamuapps.R
import com.example.suweorajamuapps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)


        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TransaksiFragment())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            val fragment = when(item.itemId){
                R.id.menu_transaksi -> TransaksiFragment()
                R.id.menu_barang -> BarangFragment()
                R.id.menu_info -> InfoFragment()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }

    }
}