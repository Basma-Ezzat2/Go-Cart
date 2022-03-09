package com.example.gocart.pojo
import com.google.gson.annotations.SerializedName


data class ProductItem (
	@SerializedName("product") val product : Product
)