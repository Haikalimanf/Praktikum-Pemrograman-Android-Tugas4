package com.example.fromfgdmvvmprojects.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fromfgdmvvmprojects.R
import com.example.fromfgdmvvmprojects.databinding.FragmentOutputBinding
import com.example.fromfgdmvvmprojects.viewmodel.AttendanceViewModel


class OutputFragment : Fragment() {

    private lateinit var binding: FragmentOutputBinding

    private val viewModel : AttendanceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOutputBinding.inflate(inflater, container, false)

        viewModel.attendanceData.observe(viewLifecycleOwner) {
            binding.txtNamaResult.text = it.nama
            binding.txtTelefonResult.text = it.telefon
            binding.txtEmailResult.text = it.email
            binding.txtGenderResult.text = it.gender
            binding.txtSkillsetResult.text = it.skillset.joinToString(", ")
            binding.txtKategoriResult.text = it.categori
        }


        return binding.root
    }


}