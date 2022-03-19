package com.example.gocart.ui.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.databinding.OrderItemBinding
import com.example.gocart.pojo.OrderObject
import com.example.gocart.utils.Constants.convertPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderAdapter (var orderList: List<OrderObject>, val viewModel: OrderViewModel, val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(var myView: OrderItemBinding) : RecyclerView.ViewHolder(myView.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val viewBinding =
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return OrderAdapter.ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            holder.myView.orderPriceId.text= convertPrice(orderList[position].price)
        }
        holder.myView.orderDateId.text= orderList[position].title.toString()

        holder.apply {
            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    
                }
            }
        }

       /* holder.myView.delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.alertDeleteMessage)
            builder.setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteOneWishItem(wishList[position].id)
            }
            builder.setNegativeButton(R.string.no, null)
            builder.show()
        }



        holder.myView.addToCart.setOnClickListener {
            val cartProduct= wishList[position]
            val product= ProductCartModule(
                cartProduct.id,
                cartProduct.title,
                cartProduct.order_state,
                cartProduct.body_html,
                cartProduct.vendor,
                cartProduct.product_type,
                cartProduct.created_at,
                cartProduct.handle,
                cartProduct.updated_at,
                cartProduct.published_at,
                cartProduct.template_suffix,
                cartProduct.status,
                cartProduct.published_scope,
                cartProduct.tags,
                cartProduct.admin_graphql_api_id,
                cartProduct.variants,
                cartProduct.options,
                cartProduct.images,
                cartProduct.image,
                cartProduct.quantitiy
            )
            viewModel.updateCard(product)
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.alertAddToCartMessage)
            builder.setPositiveButton(R.string.yes) { _, _ ->
                viewModel.updateCard(wishList[position].toProductCartModule())
            }
            builder.setNegativeButton(R.string.no, null)
            builder.show()

        }*/
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}