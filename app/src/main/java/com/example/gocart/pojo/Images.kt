package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Images (

	@SerializedName("id") val id : Long?,
	@SerializedName("product_id") val product_id : Long?,
	@SerializedName("position") val position : Int?,
	@SerializedName("created_at") val created_at : String?,
	@SerializedName("updated_at") val updated_at : String?,
	@SerializedName("alt") val alt : String?,
	@SerializedName("width") val width : Int?,
	@SerializedName("height") val height : Int?,
	@SerializedName("src") val src : String?,
	@SerializedName("variant_ids") val variant_ids : List<String>?,
	@SerializedName("admin_graphql_api_id") val admin_graphql_api_id : String?
) : Serializable