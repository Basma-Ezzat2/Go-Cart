package com.example.gocart.ui.settings.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.auth.pojo.Address
import com.example.gocart.databinding.AddressItemBinding
import com.example.gocart.databinding.SettingsAddressItemBinding
import com.example.gocart.pojo.AddressModel
import com.example.gocart.pojo.ProductCartModule

class AddressAdapter (var addressList: List<Address>, var context: Context, var deleter : (Address)->Unit) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    class ViewHolder (var myView: SettingsAddressItemBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            SettingsAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.myView.addressCountryId.text = addressList[position].city
        holder.myView.addressDetailsId.text = addressList[position].address
        holder.myView.deleteSettingsAddress.setOnClickListener {
            deleter(addressList[position])
        }

    }



    override fun getItemCount(): Int {
        return addressList.size
    }

}