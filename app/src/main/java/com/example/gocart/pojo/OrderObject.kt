package com.example.gocart.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Orders")
data class OrderObject(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id : Long = 0,
    @SerializedName("title") val title : String?,
    @SerializedName("price") val price : Double?,
    @SerializedName("src") val src : String?= null,
    @SerializedName("item_number") val item_number : String? = null)
