package com.example.gocart.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentAddressBinding
import com.example.gocart.pojo.Address


class AddressFragment : Fragment() {


    val binding by lazy {
        FragmentAddressBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addNewAddressBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
        }
    }




}