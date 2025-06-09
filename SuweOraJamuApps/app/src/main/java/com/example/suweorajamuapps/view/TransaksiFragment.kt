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
import com.example.suweorajamuapps.adapter.TransaksiAdapter
import com.example.suweorajamuapps.data.model.TransaksiDisplay
import com.example.suweorajamuapps.databinding.FragmentTransaksiBinding
import com.example.suweorajamuapps.viewmodel.TransaksiViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class TransaksiFragment : Fragment() {

    private var _binding: FragmentTransaksiBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TransaksiViewModel
    private lateinit var adapter: TransaksiAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTransaksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)

        adapter = TransaksiAdapter { transaksi ->
            val display = TransaksiDisplay(
                id = transaksi.transaksi.id,
                barangId = transaksi.barang.id,
                jumlah = transaksi.transaksi.jumlah,
                tanggal = transaksi.transaksi.tanggal
            )
            val bundle = Bundle().apply {
                putParcelable("transaksi", display)
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TransaksiFormFragment::class.java, bundle)
                .addToBackStack(null)
                .commit()
        }

        // Configure Recycleviewnya
        binding.recyclerViewTransaksi.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTransaksi.adapter = adapter

        // Viewmodel list Transaksi
        viewModel.transaksiList.observe(viewLifecycleOwner) { transaksi ->
            adapter.submitList(transaksi.toList())

            if (transaksi.isNullOrEmpty()){
                binding.textEmptyTransaksi.visibility = View.VISIBLE
                binding.recyclerViewTransaksi.visibility = View.GONE
            } else {
                binding.textEmptyTransaksi.visibility = View.GONE
                binding.recyclerViewTransaksi.visibility = View.VISIBLE
            }
            val totalBiaya = adapter.getTotalBiaya()
            val formatedTotalBiaya = NumberFormat
                .getCurrencyInstance(Locale("id", "ID"))
                .format(totalBiaya)
            binding.textTotalBiaya.text = "Total Biaya : $formatedTotalBiaya"
        }

        binding.buttonTambahTransaksi.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TransaksiFormFragment::class.java, null)
                .addToBackStack(null)
                .commit()
        }
        viewModel.loadTransaksi()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}