package com.example.gocart.data.entity.collection


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String,
    val alt: Any,
    @SerializedName("created_at")
    val createdAt: String,
    val height: Double,
    val id: Double,
    val position: Double,
    @SerializedName("product_id")
    val productId: Double,
    val src: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("variant_ids")
    val variantIds: List<Any>,
    val width: Double
)