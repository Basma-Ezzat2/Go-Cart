package com.example.gocart.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.databinding.ItemCartBinding
import com.example.gocart.pojo.ProductCartModule
import com.example.gocart.utils.Constants.convertPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartAdapter (
    var cartList: MutableList<ProductCartModule>, var decreamenter: (ProductCartModule)->Unit  ,var incremeanter: (ProductCartModule)->Unit , var deleter : (ProductCartModule)->Unit) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    class ViewHolder(var myView: ItemCartBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.myView.itemCartTitle.text = cartList[position].title
        holder.myView.itemCountText.text = cartList[position].quantitiy.toString()
        var itemPrice = 0.0
            itemPrice += cartList[position].quantitiy * (cartList[position].variants?.get(0)?.price ?: 0.0)

        CoroutineScope(Dispatchers.Main).launch {
            holder.myView.itemCartPrice.text = convertPrice(itemPrice)
        }
        holder.myView.decreaseButton.setOnClickListener{
            decreamenter(cartList[position])
        }
        holder.myView.increaseButton.setOnClickListener{
            incremeanter(cartList[position])
        }

        holder.myView.btnDelete.setOnClickListener {
            deleter(cartList[position])
        }


        Glide.with(holder.myView.itemCartImage)
            .load(cartList[position].image.src)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(holder.myView.itemCartImage)

    }

    override fun getItemCount() = cartList.size

}