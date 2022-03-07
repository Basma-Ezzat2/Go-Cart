package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName

data class ProductImages(
    @SerializedName( "images")
    val images: List<Images>? = listOf(),
)

