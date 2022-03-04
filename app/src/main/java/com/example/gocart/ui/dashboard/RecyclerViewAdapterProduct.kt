package com.example.gocart.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.data.entity.collection.Product
import com.example.gocart.databinding.CardViewProductBinding
import com.squareup.picasso.Picasso



class RecyclerViewAdapterProduct( val context:Context): RecyclerView.Adapter<RecyclerViewAdapterProduct.MyViewHolder>() {
    private var productList = mutableListOf<Product>()

    public fun addList(productList: List<Product>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = MyViewHolder(
        CardViewProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerViewAdapterProduct.MyViewHolder, position: Int) {
        holder.bind(productList[position])
//        holder.itemView.setOnClickListener {
////            clickListener.onItemEditClickProduct(productList[position])
//        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(binding: CardViewProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val tvTitle: TextView = binding.tvTitle
        private val tvPrice: TextView = binding.tvPrice
        private val ivProduct: ImageView = binding.ivProduct

        fun bind(data: Product) {
            tvTitle.text = data.title

            val url = data.image!!.src
            Picasso.get().load(url).into(ivProduct)
        }
    }

    interface OnItemClickListener {
        fun onItemEditClickProduct(book : Product)
    }

}
