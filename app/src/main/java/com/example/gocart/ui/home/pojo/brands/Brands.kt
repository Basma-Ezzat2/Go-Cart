package com.example.gocart.ui.home.pojo.brands

import com.google.gson.annotations.SerializedName

data class Brands(
    @SerializedName("smart_collections" ) var smartCollections : ArrayList<SmartCollections> = arrayListOf()
)