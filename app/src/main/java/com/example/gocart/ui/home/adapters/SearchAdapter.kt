package com.example.gocart.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.databinding.ProductItemBinding
import com.example.gocart.ui.home.pojo.search.Products

class SearchAdapter (val context: Context, val productsClickListener: ProductsClickListener) : RecyclerView.Adapter<SearchAdapter.ProductSearchViewHolder>() {

    private val productsList: ArrayList<Products> = ArrayList()

    fun addList(brandsList: ArrayList<Products>) {
        this.productsList.clear()
        this.productsList.addAll(brandsList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ProductSearchViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return productsList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ProductSearchViewHolder, position: Int) {
        val productsModel = productsList[position]

        holder.apply {

            tvTitle.text = productsModel.title

            Glide.with(context).apply {
                load(productsModel.image!!.src).into(ivProduct)
            }

            itemView.setOnClickListener {
                productsClickListener.onProductSearchClickListener(productsModel, position)
            }

        }
    }

    class ProductSearchViewHolder(binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivProduct = binding.productImage
        val tvTitle = binding.productName
    }

    interface ProductsClickListener {
        fun onProductSearchClickListener(collection: Products, position: Int)
    }

}
