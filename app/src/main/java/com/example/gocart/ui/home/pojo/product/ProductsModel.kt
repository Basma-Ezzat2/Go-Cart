package com.example.gocart.ui.home.pojo.product

import com.example.gocart.pojo.Product
import com.google.gson.annotations.SerializedName

data class ProductsModel (

  @SerializedName("products" ) var products : ArrayList<Product> = arrayListOf()

)