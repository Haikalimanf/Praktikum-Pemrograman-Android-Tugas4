package com.example.praktikfragmentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager


class FragmentB : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnToFragmentA = view.findViewById<Button>(R.id.btnToFragmentA)
        val edtData = view.findViewById<EditText>(R.id.edtData)

        val fragmentManager = parentFragmentManager

        btnToFragmentA.setOnClickListener {
            val fragmentA = FragmentA()
            val value = edtData.text.toString()

            val dataToSend: String? = if (value.isNotEmpty()) {
                value
            } else {
                null
            }

            fragmentA.arguments = Bundle().apply {
                putString("DATA_KEY", dataToSend)
            }

            fragmentManager.beginTransaction()
                .replace(R.id.framelayout, fragmentA, FragmentA::class.java.simpleName)
                .commit()
        }
    }


}