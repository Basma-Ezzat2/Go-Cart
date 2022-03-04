package com.example.gocart.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.databinding.BrandItemBinding
import com.example.gocart.ui.home.pojo.brands.SmartCollections

class BrandsAdapter(val context: Context, private val brandsClickListener: BrandsClickListener) :
    RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {

    private val brandsList: ArrayList<SmartCollections> = ArrayList()

    fun addList(brandsList: ArrayList<SmartCollections>) {
        this.brandsList.clear()
        this.brandsList.addAll(brandsList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = BrandsViewHolder(
        BrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return brandsList.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val collectionItem = brandsList[position]

        holder.apply {

            tvTitle.text = collectionItem.title

            Glide.with(context).apply {
                load(collectionItem.image!!.src).into(ivProduct)
            }

            itemView.setOnClickListener {
                brandsClickListener.brandClick(collectionItem, position)
            }

        }
    }

    class BrandsViewHolder(binding: BrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivProduct = binding.ivProduct
        val tvTitle = binding.tvTitle
    }

    interface BrandsClickListener {
        fun brandClick(collection: SmartCollections, position: Int)
    }

}
