package com.example.gocart.ui.wishList

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.databinding.WishListItemBinding
import com.example.gocart.pojo.Product
import com.example.gocart.ui.home.adapters.ProductAdapter
import com.example.gocart.utils.Constants.convertPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishListAdapter(var wishList: List<Product>, val viewModel: WishListViewModel, val context: Context, private val productsClickListener: WishListFragment) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    class ViewHolder(var myView: WishListItemBinding) : RecyclerView.ViewHolder(myView.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.ViewHolder {
        val viewBinding =
            WishListItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return WishListAdapter.ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: WishListAdapter.ViewHolder, position: Int) {
       val productsModel = wishList[position]

        holder.myView.title.text= wishList[position].title
        CoroutineScope(Dispatchers.Main).launch {
            holder.myView.tvPrice.text= convertPrice(wishList[position].variants?.get(0)?.price)
        }
        Glide.with(holder.myView.itemImage.context)
            .load(wishList[position].image.src)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(holder.myView.itemImage)

        holder.myView.delete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.alertDeleteMessage)
            builder.setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteOneWishItem(wishList[position].id)
            }
            builder.setNegativeButton(R.string.no, null)
            builder.show()
        }

        holder.apply {
            itemView.setOnClickListener {
                productsClickListener.onProductClickListener(productsModel, position)
            }
        }


        /*holder.myView.addToCart.setOnClickListener {
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
        return wishList.size
    }

    interface ProductsClickListener {
        fun onProductClickListener(collection: Product, position: Int)
    }

}