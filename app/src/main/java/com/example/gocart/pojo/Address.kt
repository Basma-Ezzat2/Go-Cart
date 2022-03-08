package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(

    @SerializedName( "id")
    val id: Long? = 0,

    @SerializedName( "country")
    val country: String? = "",

    @SerializedName( "city")
    val city: String? = "",

    @SerializedName( "first_name")
    val firstName: String? = "",

    @SerializedName( "last_name")
    val lastName: String? = "",

    @SerializedName( "address")
    val address: String? = "",

    @SerializedName( "phone")
    val phone: String? = "",

    @SerializedName( "default")
    val default: Boolean = false,

    ):Serializable
