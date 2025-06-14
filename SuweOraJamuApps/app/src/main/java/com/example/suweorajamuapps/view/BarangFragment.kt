package com.example.suweorajamuapps.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suweorajamuapps.R
import com.example.suweorajamuapps.adapter.BarangAdapter
import com.example.suweorajamuapps.databinding.FragmentBarangBinding
import com.example.suweorajamuapps.viewmodel.BarangViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BarangFragment : Fragment() {

    private var _binding: FragmentBarangBinding? = null
    private val binding get() = _binding!!

    private lateinit var barangAdapter : BarangAdapter

    private lateinit var viewModel: BarangViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBarangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BarangViewModel::class.java)

        barangAdapter = BarangAdapter { barang ->
            val bundle = Bundle().apply {
                putParcelable("barang", barang)
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BarangFormFragment::class.java, bundle)
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerViewBarang.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewBarang.adapter = barangAdapter

        viewModel.barangList.observe(viewLifecycleOwner) { barangList ->
            barangAdapter.submitList(barangList)

            if (barangList.isNullOrEmpty()){
                binding.textEmptyState.visibility = View.VISIBLE
                binding.recyclerViewBarang.visibility = View.GONE
            } else {
                binding.textEmptyState.visibility = View.GONE
                binding.recyclerViewBarang.visibility = View.VISIBLE
            }
        }

        binding.buttonTambahBarang.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BarangFormFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }

        viewModel.loadBarang()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}