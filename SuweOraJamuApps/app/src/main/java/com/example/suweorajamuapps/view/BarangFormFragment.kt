package com.example.suweorajamuapps.view

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.suweorajamuapps.R
import com.example.suweorajamuapps.data.model.Barang
import com.example.suweorajamuapps.databinding.FragmentBarangFormBinding
import com.example.suweorajamuapps.utils.ImageUtils.copyImageToInternalStorage
import com.example.suweorajamuapps.viewmodel.BarangViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class BarangFormFragment : Fragment() {

    private var _binding: FragmentBarangFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BarangViewModel by viewModels()
    private var barang: Barang? = null
    private var imageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let{
            val safeUri = copyImageToInternalStorage(requireContext(), it)
            safeUri?.let { newUri ->
                imageUri = newUri
                binding.imagePreview.setImageURI(newUri)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBarangFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barang = arguments?.getParcelable("barang")

        barang?.let {
            binding.editNama.setText(it.nama)
            binding.editHarga.setText(it.harga.toString())
            imageUri = Uri.parse(it.imageUri)

            val file = File(imageUri?.path ?: "")
            if (file.exists()) {
                binding.imagePreview.setImageURI(imageUri)
            } else {
                binding.imagePreview.setImageResource(R.drawable.placeholder)
            }

            binding.buttonHapusBarang.visibility = View.VISIBLE
        }

        binding.imagePreview.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.buttonPilihDariGaleri.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.buttonSimpanBarang.setOnClickListener {
            val nama = binding.editNama.text.toString()
            val harga = binding.editHarga.text.toString().toDoubleOrNull()
            if (nama.isBlank() || harga == null || imageUri == null) {
                Toast.makeText(requireContext(), "Isi semua field dan pilih gambar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.progressBar.visibility = View.VISIBLE
            binding.buttonSimpanBarang.isEnabled = false

            lifecycleScope.launch {
                val newBarang = Barang(
                    id = barang?.id ?: 0,
                    nama = nama,
                    harga = harga,
                    imageUri = imageUri?.toString() ?: ""
                )

                if (barang == null) {
                    viewModel.insertBarang(newBarang)
                    Toast.makeText(requireContext(), "Barang Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateBarang(newBarang)
                    Toast.makeText(requireContext(), "Barang Berhasil Diupdate", Toast.LENGTH_SHORT).show()
                }

                delay(500)
                binding.progressBar.visibility = View.GONE
                binding.buttonSimpanBarang.isEnabled = true
                parentFragmentManager.popBackStack()
            }
        }

        binding.buttonHapusBarang.setOnClickListener {
            barang?.let {
                AlertDialog.Builder(requireContext())
                    .setTitle("Hapus Barang")
                    .setMessage("Apakah Anda yakin ingin menghapus barang ini?")
                    .setPositiveButton("Ya") { _, _ ->
                        lifecycleScope.launch {
                            viewModel.deleteBarang(it)
                            Toast.makeText(requireContext(), "Barang Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                            parentFragmentManager.popBackStack()
                        }
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}