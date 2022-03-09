package com.example.gocart.data.entity.categoriesPojo

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf(),
  @SerializedName("product_type" ) var productType : ArrayList<Products> = arrayListOf()


)