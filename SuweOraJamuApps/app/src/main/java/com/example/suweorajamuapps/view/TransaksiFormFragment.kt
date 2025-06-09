package com.example.suweorajamuapps.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.suweorajamuapps.R
import com.example.suweorajamuapps.data.model.Transaksi
import com.example.suweorajamuapps.data.model.TransaksiDisplay
import com.example.suweorajamuapps.databinding.FragmentTransaksiFormBinding
import com.example.suweorajamuapps.utils.DateTimePickerUtil
import com.example.suweorajamuapps.viewmodel.TransaksiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TransaksiFormFragment : Fragment() {

    private var _binding: FragmentTransaksiFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransaksiViewModel by viewModels()
    private var transasksiDisplay: TransaksiDisplay? = null
    private var selectedBarangId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTransaksiFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transasksiDisplay = arguments?.getParcelable("transaksi")

        viewModel.barangList.observe(viewLifecycleOwner) { barangList ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                barangList.map { it.nama }
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.spinnerBarang.adapter = adapter

            transasksiDisplay?.let { transaksiItem ->
                val index = barangList.indexOfFirst { transaksiItem.id == transaksiItem.barangId }

                if (index >= 0) {
                    binding.spinnerBarang.setSelection(index)
                    binding.editJumlah.setText(transaksiItem.jumlah.toString())
                    binding.editTanggal.setText(transaksiItem.tanggal)
                    binding.buttonSimpan.visibility = View.VISIBLE
                } else {
                    binding.buttonSimpan.visibility = View.GONE
                }
            }

            // Datetimepicker
            binding.editTanggal.setOnClickListener {
                DateTimePickerUtil.showDateTimePicker(requireContext()) { selectedDateTime ->
                    binding.editTanggal.setText(selectedDateTime)
                }
            }

            binding.buttonSimpan.setOnClickListener {
                val jumlahStr = binding.editJumlah.text.toString()
                val tanggal = binding.editTanggal.text.toString()
                val jumlah = jumlahStr.toIntOrNull()
                selectedBarangId = barangList.getOrNull(binding.spinnerBarang.selectedItemPosition)?.id

                if (selectedBarangId == null || selectedBarangId==0) {
                    Toast.makeText(requireContext(), "Isi semua field", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (jumlah == null || tanggal.isBlank() || jumlah <= 0) {
                    Toast.makeText(requireContext(), "Jumlah harus lebih dari O", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (tanggal.isBlank()) {
                    Toast.makeText(requireContext(), "Tanggal harus diisi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val newTransaksi = Transaksi(
                    id = transasksiDisplay?.id ?: 0,
                    barangId = selectedBarangId!!,
                    jumlah = jumlah,
                    tanggal = tanggal
                )

                binding.progressBar.visibility = View.VISIBLE
                binding.buttonSimpan.isEnabled = false

                lifecycleScope.launch {
                    if (transasksiDisplay == null) {
                        viewModel.insertTransaksi(newTransaksi)
                        delay(500)
                        viewModel.loadTransaksi()
                        Toast.makeText(requireContext(), "Transaksi Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.updateTransaksi(newTransaksi)
                        delay(500)
                        viewModel.loadTransaksi()
                        Toast.makeText(requireContext(), "Transaksi Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                    }
                    delay(500)
                    binding.progressBar.visibility = View.GONE
                    binding.buttonSimpan.isEnabled = true
                    parentFragmentManager.popBackStack()
                }

            }
            binding.buttonHapusTransaksi.setOnClickListener {
                transasksiDisplay?.let {transasksiToDelete ->
                    AlertDialog.Builder(requireContext())
                        .setTitle("Konfirmasi Hapus")
                        .setMessage("Apakah Anda yakin ingin menghapus transaksi ini?")
                        .setPositiveButton("Ya") { _, _ ->
                            lifecycleScope.launch {
                                viewModel.deleteTransaksi(transasksiToDelete.toTransaksi())
                                delay(500)
                                viewModel.loadTransaksi()
                                parentFragmentManager.popBackStack()
                            }
                        }
                        .setNegativeButton("Tidak", null)
                        .show()
                }
            }
        }
        viewModel.loadBarang()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}