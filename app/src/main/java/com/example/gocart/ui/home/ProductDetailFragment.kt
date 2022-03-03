package com.example.gocart.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gocart.R
import com.example.gocart.databinding.FragmentProductDetailBinding


class ProductDetailFragment : Fragment() {

   private lateinit var binding:FragmentProductDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProductDetailBinding.inflate(inflater)
        return binding.root
    }


}