package com.example.gocart.ui.home.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.R

import com.example.gocart.databinding.ProductDeatilsActivityBinding
import com.example.gocart.pojo.Product
import com.example.gocart.pojo.Variants
import com.example.gocart.ui.home.adapters.ProductImagesAdapter
import com.example.gocart.ui.home.viewmodels.HomeViewModel
import com.example.gocart.utils.Constants.convertPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        }


        productImagesAdapter = ProductImagesAdapter(this)
        homeViewModel.getProductDetails(intent.extras!!.getLong("product_id"))


        homeViewModel.productDetails.observe(this, {
            if (it.images.isNotEmpty()) {
                binding.detailsProgress.visibility = View.GONE
                binding.scrollView.visibility = View.VISIBLE
                binding.toolbar3.title = it.title
            }



            productImagesAdapter.setContentList(it.images)
            binding.viewPager2.adapter = productImagesAdapter
            binding.txtdescription.text = it.bodyHtml
            binding.tvProductName.text = it.title.toString()
        })

        homeViewModel.varients.observe(this, {
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvPrice.text = convertPrice(it.price?.toDouble())
            }

            binding.addToCart.setOnClickListener { view ->
                val product = intent.extras!!.getSerializable("cart_product") as Product
                homeViewModel.addToCart(
                    product.copy(
                        variants = listOf(
                            Variants(
                                id = it.id,
                                price = it.price!!.toDouble()
                            )
                        )
                    )
                )
                Toast.makeText(applicationContext, resources.getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
            }
            binding.cbHeart.setOnClickListener {view ->
                val product = intent.extras!!.getSerializable("cart_product") as Product
                homeViewModel.saveWishList(
                    product.copy(
                        variants = listOf(
                            Variants(
                                id = it.id,
                                price = it.price!!.toDouble()
                        )
                )
                    )
                )
                //Toast.makeText(applicationContext, "Added To Wishlist", Toast.LENGTH_SHORT).show()
            }

            binding.cbHeart.setOnCheckedChangeListener { checkBox, isChecked ->

                if (isChecked) {
                    showToast(resources.getString(R.string.added_to_wishlist))
                } else {
                    /*showToast("Item remov   ed from Wishlist")
                    homeViewModel.deleteOneWishItem(it.id!!)*/
                }
            }
        })


    }
    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    fun onAddToCart(collection: Product, position: Int) {
        homeViewModel.addToCart(collection)
    }
}