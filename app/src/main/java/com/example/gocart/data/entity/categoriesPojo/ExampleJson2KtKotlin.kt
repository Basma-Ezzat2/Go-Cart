package com.example.gocart.data.entity.categoriesPojo

import com.example.gocart.pojo.Product
import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("products" ) var products : ArrayList<Product> = arrayListOf(),
  @SerializedName("product_type" ) var productType : ArrayList<Product> = arrayListOf()


)