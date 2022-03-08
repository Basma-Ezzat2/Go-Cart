package com.example.gocart.ui.home.pojo.search

import com.google.gson.annotations.SerializedName


data class SearchProduct (

  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf()

)