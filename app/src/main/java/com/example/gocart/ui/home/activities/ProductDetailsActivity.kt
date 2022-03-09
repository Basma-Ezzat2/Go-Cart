package com.example.gocart.ui.home.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.R

import com.example.gocart.databinding.ProductDeatilsActivityBinding
import com.example.gocart.pojo.Product
import com.example.gocart.ui.home.adapters.ProductImagesAdapter
import com.example.gocart.ui.home.fragment.ReviewFragment
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

        title = ""



        binding.toolbar3.setNavigationOnClickListener { finish() }
        binding.ratingCount.rating = 3.5f
        binding.review.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
//////            findNavController(this.binding.root).navigate(R.id.reviewFragment)
//            val pendingIntent = NavDeepLinkBuilder(this.applicationContext)
//                .setGraph(R.navigation.mobile_navigation)
//                .setDestination(R.id.reviewFragment)
//                .createPendingIntent()
//
//            pendingIntent.send()
        }


        productImagesAdapter = ProductImagesAdapter(this)
        homeViewModel.getProductDetails(intent.extras!!.getLong("product_id"))


        homeViewModel.productDetails.observe(this, {
            if (it.images.isNotEmpty()) {
                binding.detailsProgress.visibility = View.GONE
                binding.scrollView.visibility = View.VISIBLE
                binding.toolbar3.title = it.title


            }
            binding.addToCart.setOnClickListener {view ->
                val product = intent.extras!!.getSerializable("cart_product") as Product
                homeViewModel.addToCart(product)
            }

            productImagesAdapter.setContentList(it.images)
            binding.viewPager2.adapter = productImagesAdapter
            binding.txtdescription.text = it.bodyHtml
            binding.tvProductName.text = it.title.toString()
        })

        homeViewModel.varients.observe(this, {
            binding.tvPrice.text = it.price.toString() + "$"
        })



    }

    fun onAddToCart(collection: Product, position: Int) {
        homeViewModel.addToCart(collection)
    }
}