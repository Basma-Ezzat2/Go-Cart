package com.example.gocart.pojo

import com.example.gocart.pojo.Address
import com.google.gson.annotations.SerializedName


data class AddressModelT(
    @SerializedName( "address")
    val address: Address?,
)
