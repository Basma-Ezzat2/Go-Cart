package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Variants (

    @SerializedName("id") val id : Long? ,
    @SerializedName("product_id") val product_id : Long? = null,
    @SerializedName("title") val title : String? = null,
    @SerializedName("price") val price : Double?,
    @SerializedName("sku") val sku : String? = null,
    @SerializedName("position") val position : Int? = null,
    @SerializedName("inventory_policy") val inventory_policy : String? = null,
    @SerializedName("compare_at_price") val compare_at_price : String? = null,
    @SerializedName("fulfillment_service") val fulfillment_service : String? = null,
    @SerializedName("inventory_management") val inventory_management : String? = null,
    @SerializedName("option1") val option1 : String? = null,
    @SerializedName("option2") val option2 : String? = null,
    @SerializedName("option3") val option3 : String? = null,
    @SerializedName("created_at") val created_at : String? = null,
    @SerializedName("updated_at") val updated_at : String?= null,
    @SerializedName("taxable") val taxable : Boolean?= null,
    @SerializedName("barcode") val barcode : String?= null,
    @SerializedName("grams") val grams : Int?= null,
    @SerializedName("image_id") val image_id : String?= null,
    @SerializedName("weight") val weight : Int?= null,
    @SerializedName("weight_unit") val weight_unit : String?= null,
    @SerializedName("inventory_item_id") val inventory_item_id : Long?= null,
    @SerializedName("inventory_quantity") var inventory_quantity : Int?= null,
    @SerializedName("old_inventory_quantity") val old_inventory_quantity : Int?= null,
    @SerializedName("requires_shipping") val requires_shipping : Boolean?= null,
    @SerializedName("admin_graphql_api_id") val admin_graphql_api_id : String?= null
): Serializable
