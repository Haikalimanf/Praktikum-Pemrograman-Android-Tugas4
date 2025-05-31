package com.example.praktikfragmentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class FragmentA : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val btnToFragmentB = view.findViewById<Button>(R.id.btnToFragmentB)
        val textValue = view.findViewById<TextView>(R.id.txtResult)

        val fragmentManager = parentFragmentManager

        val dataFromFragmentB = arguments?.getString("DATA_KEY")

        btnSubmit.setOnClickListener {
            Toast.makeText(requireContext(), "Submit di halaman ini", Toast.LENGTH_SHORT).show()
        }

        textValue.text = dataFromFragmentB.toString()

        btnToFragmentB.setOnClickListener {

            val fragmentB = FragmentB()

            fragmentManager.beginTransaction()
                .replace(R.id.framelayout, fragmentB, FragmentB::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }


}