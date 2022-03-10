package com.example.gocart.auth.pojo

import com.example.gocart.auth.pojo.Customer
import com.google.gson.annotations.SerializedName


data class CustomersModel(
    @SerializedName( "customers")
    val customer: List<Customer?>,

    @SerializedName( "error")
    val error: Error? = null,
)
