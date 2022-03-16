package com.example.gocart.pojo

import com.example.gocart.pojo.CustomerAddress
import com.google.gson.annotations.SerializedName

data class CustomerAddressModel(
    @SerializedName( "customer_address")
    val customerAddress: CustomerAddress?,
)
