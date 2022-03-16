package com.example.gocart.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.databinding.OrderItemBinding
import com.example.gocart.pojo.OrderObject

class MeOrderAdapter(var orderList: List<OrderObject>, val viewModel: MeViewModel, val context: Context) : RecyclerView.Adapter<MeOrderAdapter.ViewHolder>() {

    class ViewHolder(var myView: OrderItemBinding) : RecyclerView.ViewHolder(myView.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeOrderAdapter.ViewHolder {
        val viewBinding =
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return MeOrderAdapter.ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MeOrderAdapter.ViewHolder, position: Int) {
        holder.myView.orderPriceId.text= orderList[position].price.toString()
        holder.myView.orderDateId.text= orderList[position].id.toString()


        /*holder.myView.delete.setOnClickListener {
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
        }*/
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}