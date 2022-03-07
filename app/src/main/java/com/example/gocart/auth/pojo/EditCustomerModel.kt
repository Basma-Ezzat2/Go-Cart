package com.example.gocart.auth.pojo

import com.google.gson.annotations.SerializedName

data class EditCustomerModel(
    @SerializedName( "customer")
    val customer: Customer?,

    @SerializedName( "error")
    val error: Error? = null,
)
