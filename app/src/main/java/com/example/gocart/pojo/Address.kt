package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(

    @SerializedName( "id")
    var id: Long? = 0,

    @SerializedName( "country")
    var country: String? = "",

    @SerializedName( "city")
    var city: String? = "",

    @SerializedName( "first_name")
    var firstName: String? = "",

    @SerializedName( "address1")
    var address: String? = "",

    @SerializedName( "phone")
    var phone: String? = "",

    @SerializedName( "default")
    var default: Boolean = false,

    ):Serializable
