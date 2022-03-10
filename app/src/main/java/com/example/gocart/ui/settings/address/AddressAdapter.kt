package com.example.gocart.ui.settings.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.databinding.AddressItemBinding
import com.example.gocart.databinding.SettingsAddressItemBinding
import com.example.gocart.pojo.Address

class AddressAdapter (var addressList: List<Address>, var context: Context) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    class ViewHolder (var myView: SettingsAddressItemBinding) : RecyclerView.ViewHolder(myView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            SettingsAddressItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.myView.addressCountryId.text = addressList[position].country
        holder.myView.addressDetailsId.text = addressList[position].address}

    override fun getItemCount(): Int {
        return addressList.size
    }

}