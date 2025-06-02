package com.example.fromfgdmvvmprojects.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.fromfgdmvvmprojects.R
import com.example.fromfgdmvvmprojects.databinding.FragmentInputBinding
import com.example.fromfgdmvvmprojects.model.AttendanceModel
import com.example.fromfgdmvvmprojects.viewmodel.AttendanceViewModel


class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: AttendanceViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        // Pengisian spiner dengan string array
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.kategori_peserta,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spCategory.adapter = it
        }

        // Pengambilan value dari field view
        binding.btnSubmit.setOnClickListener {
            val model = AttendanceModel(
                nama = binding.edtNama.text.toString(),
                telefon = binding.edtTelefon.text.toString(),
                email = binding.edtEmail.text.toString(),
                gender = if (binding.radLaki.isChecked) "laki-laki" else "perempuan",
                skillset = listOfNotNull(
                    if (binding.chkAlgo.isChecked) "Algoritma" else "",
                    if (binding.chkProblemSolving.isChecked) "Problem Solving" else "",
                    if (binding.chkProgramming.isChecked) "Programming" else "",
                    if (binding.chkDesignThinking.isChecked) "Design Thinking" else "",
                    if (binding.chkCriticalThinking.isChecked) "Critical Thinking" else "",
                ),
                categori = binding.spCategory.selectedItem.toString() ?: "Tidak diketahui"
            )

            viewModel.setAttendanceData(model)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OutputFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}