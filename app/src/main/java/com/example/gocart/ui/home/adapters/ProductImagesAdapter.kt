package com.example.gocart.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.ui.home.pojo.productdetail.Images
import com.makeramen.roundedimageview.RoundedImageView


class ProductImagesAdapter(val context: Context) : RecyclerView.Adapter<ProductImagesAdapter.PrImages>() {

    private var list: ArrayList<Images> = ArrayList()

    fun setContentList(list: ArrayList<Images>) {
        this.list.clear()
        this.list.addAll(list)

    }

    inner class PrImages(view: View) : RecyclerView.ViewHolder(view) {
        var imageView = itemView.findViewById<RoundedImageView>(R.id.list_item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrImages {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return PrImages(view)
    }

    override fun onBindViewHolder(holder: PrImages, position: Int) {
        val image = list[position]
        holder.apply {
            Glide.with(context).load(image.src).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}