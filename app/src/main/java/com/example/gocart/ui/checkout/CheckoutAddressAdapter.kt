package com.example.gocart.ui.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.auth.pojo.Address
import com.example.gocart.databinding.AddressItemBinding
import com.example.gocart.databinding.SettingsAddressItemBinding
import com.example.gocart.ui.settings.address.AddressAdapter

class CheckoutAddressAdapter (var addressList: List<Address>, var context: Context) : RecyclerView.Adapter<CheckoutAddressAdapter.ViewHolder>() {

    class ViewHolder (var myView: AddressItemBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            AddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.myView.checkoutAddressCountryId.text = addressList[position].city
        holder.myView.checkoutAddressDetailsId.text = addressList[position].address


    }



    override fun getItemCount(): Int {
        return addressList.size
    }

}