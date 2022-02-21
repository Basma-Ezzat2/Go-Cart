package com.example.gocart.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.makeramen.roundedimageview.RoundedImageView

class AdsAdapter(val context: Context) : RecyclerView.Adapter<AdsAdapter.AdsViewHolder>() {

    lateinit var list: List<Int>
    fun setContentList(list: List<Int>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = itemView.findViewById<RoundedImageView>(R.id.list_item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsAdapter.AdsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return AdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdsAdapter.AdsViewHolder, position: Int) {
        holder.image.setImageResource(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }



}