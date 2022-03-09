package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Options (

	@SerializedName("id") val id : Long?,
	@SerializedName("product_id") val product_id : Long?,
	@SerializedName("name") val name : String?,
	@SerializedName("position") val position : Int?,
	@SerializedName("values") var values : List<String>?
): Serializable