package com.example.gocart.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.databinding.FragmentProductDetailBinding
import com.example.gocart.ui.home.adapters.ProductImagesAdapter
import com.example.gocart.ui.home.viewmodels.HomeViewModel


class ProductDetailFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        productImagesAdapter = ProductImagesAdapter(requireContext())

        homeViewModel.productDetails.observe(viewLifecycleOwner, {
            productImagesAdapter.setContentList(it.images)
            binding.viewPager2.adapter = productImagesAdapter
            binding.txtdescription.text = it.bodyHtml
            binding.tvProductName.text = it.title.toString()
        })

        homeViewModel.varients.observe(viewLifecycleOwner, {
            binding.tvPrice.text = it.price.toString()
            Toast.makeText(requireContext(), it.compareAtPrice.toString(), Toast.LENGTH_LONG).show()
        })
        return binding.root
    }
}