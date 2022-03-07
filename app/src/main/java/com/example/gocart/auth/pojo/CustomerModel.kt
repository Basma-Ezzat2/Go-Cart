package com.example.gocart.auth.pojo

import com.example.gocart.auth.pojo.Customer
import com.google.gson.annotations.SerializedName

data class CustomerModel (
    @SerializedName( "customer")
    val customer: Customer?,

    @SerializedName( "error")
    val error: Error? = null,
)

