package com.example.gocart.data.entity.collection


import com.google.gson.annotations.SerializedName

data class Option(
    val id: Double,
    val name: String,
    val position: Double,
    @SerializedName("product_id")
    val productId: Double
)