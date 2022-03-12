package com.example.gocart.pojo


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Orders(
    val order: Order?
)

data class DiscountCodess(
    val amount: String?,
    val code: String?
): Serializable

data class Order(
    val customer: CustomerOrder?,
    @SerializedName("financial_status")
    val financialStatus: String?,
    @SerializedName("line_items")
    val lineItems: List<LineItem>?,
    val note: String?,
    @SerializedName("discount_codes")
    val discountCodes: List<DiscountCodes>?
)

data class LineItem(
    val quantity: Int?,
    @SerializedName("variant_id")
    val variantId: Long?
)

data class CustomerOrder(
    val id: Long?
)
