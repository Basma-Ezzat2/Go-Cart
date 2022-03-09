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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartAdapter (
    var cartList: List<ProductCartModule>, var decreamenter: (ProductCartModule)->Unit  ,var incremeanter: (ProductCartModule)->Unit , var deleter : (ProductCartModule)->Unit) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    /*fun addNewList(orderNewList: List<ProductCartModule>) {
        cartList.clear()
        cartList.addAll(orderNewList)
        notifyDataSetChanged()

    }
    fun delItem(pos:Int) {
        cartList.removeAt(pos);
        notifyItemRemoved(pos);

    }*/


    class ViewHolder(var myView: ItemCartBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.myView.itemCartTitle.text = cartList[position].title
        holder.myView.itemCountText.text = cartList[position].quantitiy.toString()
        holder.myView.itemCartPrice.text = cartList[position].variants?.get(0)?.price.toString()+" USD"
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


        /* holder.myView.decreaseButton.setOnClickListener {
             var num =((holder.myView.itemCountText.text.toString().toInt())-1)
             if(num>0){
                 cartList[position].variants?.get(0)?.inventory_quantity = num
                 holder.myView.itemCountText.text=num.toString()
                 cartViewModel.onChangeQuntity()
             }
             else{
                 cartViewModel.onDelClick( cartList[position].id)
             }
         }
         holder.myView.increaseButton.setOnClickListener {
             var num =((holder.myView.itemCountText.text.toString().toInt())+1)
             cartList[position].variants?.get(0)?.inventory_quantity = num
             holder.myView.itemCountText.text=num.toString()
             cartViewModel.onChangeQuntity()
         }
         holder.myView.btnFav.setOnClickListener {
             cartViewModel.onFavClick( cartList[position])
         }
         holder.myView.itemCartImage.setOnClickListener {

             cartViewModel.onImgClick( cartList[position].id)
         }*/
    }

    override fun getItemCount() = cartList.size

}