package com.example.gocart.ui.home.pojo.productdetail

import com.google.gson.annotations.SerializedName


data class ProductDetails (

  @SerializedName("product" ) var product : Product? = Product()

)