package com.example.gocart.ui.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gocart.R
import com.example.gocart.databinding.FragmentSalesBinding

class SalesFragment : Fragment() {
    private lateinit var _binding: FragmentSalesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_sales, container, false)
        _binding = FragmentSalesBinding.inflate(inflater, container, false)
        return _binding.root

    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}