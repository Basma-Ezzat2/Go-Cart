package com.example.gocart.ui.home.pojo.product

import com.google.gson.annotations.SerializedName

data class ProductsModel (

  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf()

)