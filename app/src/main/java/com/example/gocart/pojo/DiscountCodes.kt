package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName

data class DiscountCodes(
    @SerializedName( "code")
    val code: String? = "",

    @SerializedName( "amount")
    val amount: String? = "10.0",

    @SerializedName( "type")
    val type: String? = "percentage",
)
