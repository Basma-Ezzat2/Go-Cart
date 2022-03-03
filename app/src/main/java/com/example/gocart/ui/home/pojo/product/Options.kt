package com.example.gocart.ui.home.pojo.product

import com.google.gson.annotations.SerializedName


data class Options (

  @SerializedName("id"         ) var id        : Long?    = null,
  @SerializedName("product_id" ) var productId : Long?    = null,
  @SerializedName("name"       ) var name      : String? = null,
  @SerializedName("position"   ) var position  : Int?    = null

)