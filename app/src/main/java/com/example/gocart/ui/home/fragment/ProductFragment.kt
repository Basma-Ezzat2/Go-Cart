package com.example.gocart.ui.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentProductBinding
import com.example.gocart.pojo.Product
import com.example.gocart.ui.home.adapters.ProductAdapter
import com.example.gocart.ui.home.pojo.product.Products
import com.example.gocart.ui.home.viewmodels.HomeViewModel

class ProductFragment : Fragment(), ProductAdapter.ProductsClickListener {

    private lateinit var binding: FragmentProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater)
        productAdapter = ProductAdapter(requireContext(), this)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        viewModel.productsByBrand.observe(viewLifecycleOwner, {
            if (it.products.isNotEmpty()) {
                binding.progressBar2.visibility = View.GONE
            }
            productAdapter.addList(it.products)
            binding.productsList.adapter = productAdapter
        })
        toolbarConfig()
        return binding.root
    }

    private fun toolbarConfig() {
        activity!!.findViewById<Toolbar>(R.id.toolbar).apply {
            title = arguments!!.getString("BrandName")
            setNavigationOnClickListener { findNavController().navigate(R.id.navigation_home) }
            setNavigationIcon(R.drawable.ic_arrow_back)
        }
    }


    override fun onProductClickListener(collection: Product, position: Int) {
        viewModel.getProductDetails(collection.vendor)
        Toast.makeText(requireActivity(), collection.id.toString(), Toast.LENGTH_LONG).show()
        Log.d("ayaa", "onProductClickListener: " + collection.id)
        viewModel.addToCart(collection)
    }


}