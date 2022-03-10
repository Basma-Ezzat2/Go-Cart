package com.example.gocart.auth.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(

    @SerializedName( "id")
    val id: Long? = 0,

    @SerializedName( "address1")
    val address: String? = "",

    @SerializedName( "city")
    val city: String? = "",

    @SerializedName( "first_name")
    val firstName: String? = "",

    @SerializedName( "last_name")
    val lastName: String? = "",

    @SerializedName( "zip")
    val zip: String? = "",

    ):Serializable
