package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName

data class DiscountModel(
    @SerializedName( "price_rules")
    val discount: List<PriceRule>?,
)