package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName

data class Discount(
    @SerializedName( "price_rule")
    val discount: PriceRule?,
)
