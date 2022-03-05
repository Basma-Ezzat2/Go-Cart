package com.example.gocart.ui.home.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.ProductDeatilsActivityBinding
import com.example.gocart.ui.home.adapters.ProductImagesAdapter
import com.example.gocart.ui.home.viewmodels.HomeViewModel

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ProductDeatilsActivityBinding
    private lateinit var productImagesAdapter: ProductImagesAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDeatilsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.toolbar3.setNavigationOnClickListener { finish() }


        productImagesAdapter = ProductImagesAdapter(this)
        homeViewModel.getProductDetails(intent.extras!!.getLong("product_id"))


        homeViewModel.productDetails.observe(this, {
            if (it.images.isNotEmpty()) {
                binding.detailsProgress.visibility = View.GONE
                binding.scrollView.visibility = View.VISIBLE
            }

            productImagesAdapter.setContentList(it.images)
            binding.viewPager2.adapter = productImagesAdapter
            binding.txtdescription.text = it.bodyHtml
            binding.tvProductName.text = it.title.toString()
        })

        homeViewModel.varients.observe(this, {
            binding.tvPrice.text = it.price.toString()
        })
    }
}