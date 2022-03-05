package com.stash.shopeklobek.model.entities

import com.example.gocart.auth.pojo.Customer
import com.google.gson.annotations.SerializedName


data class CustomerLoginModel(
    @SerializedName( "customers")
    val customer: List<Customer?>,

    @SerializedName( "error")
    val error: Error? = null,
)
