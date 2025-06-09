package com.example.suweorajamuapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.suweorajamuapps.R
import com.example.suweorajamuapps.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return TextView(requireContext()).apply {
            text = "Aplikasi Warung Jamu Suwe Ora Jamu \n" +
                    "Created By : Haikal Iman F\n" +
                    "Email : haikalimanf@gmail.com"
            setPadding(16,16,16,16)
            textSize = 18f
        }
    }




}