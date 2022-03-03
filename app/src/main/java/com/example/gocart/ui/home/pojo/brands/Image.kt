package com.example.gocart.ui.home.pojo.brands

import com.google.gson.annotations.SerializedName


data class Image (
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("alt"        ) var alt       : String? = null,
    @SerializedName("width"      ) var width     : Int?    = null,
    @SerializedName("height"     ) var height    : Int?    = null,
    @SerializedName("src"        ) var src       : String? = null

)