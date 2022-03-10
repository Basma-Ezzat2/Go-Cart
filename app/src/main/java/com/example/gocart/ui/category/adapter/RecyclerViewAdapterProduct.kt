package com.example.gocart.ui.category.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.data.entity.categoriesPojo.Products
import com.example.gocart.databinding.CardViewProductBinding


class RecyclerViewAdapterProduct(val context:Context, private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapterProduct.MyViewHolder>() {
    private var productList = mutableListOf<Products>()
    fun addList(productList: List<Products>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MyViewHolder(
        CardViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productsModel = productList[position]

        holder.apply {
            tvDesc.text = productsModel.title
            Glide.with(context).apply {
                load(productsModel.image!!.src).into(imageProduct)
            }

            itemView.setOnClickListener {
                onItemClickListener.onItemEditClickProduct(productsModel,position)
            }

        }
    }

    class MyViewHolder(binding: CardViewProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvDesc: TextView = binding.tvDesc
        //private val tvPrice: TextView = binding.tvPrice
        val imageProduct: ImageView = binding.imageProduct

    }

    interface OnItemClickListener {
        fun onItemEditClickProduct(book : Products, position:Int)
    }

}
