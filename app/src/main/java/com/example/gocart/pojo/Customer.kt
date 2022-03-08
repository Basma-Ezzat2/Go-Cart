package com.example.gocart.pojo

import com.google.gson.annotations.SerializedName

data class Customers(
    val customers: List<Customer>?
)

data class CustomerX(
    val customer: Customer
)

data class CustomerProfile(
    val customer: CustomerXXX?
)

data class CustomerXXX(
    @SerializedName("first_name")
    val firstName: String?,
    val id: Long?,
    val note: String?,
    val phone: String?
)
data class Customer(
    @SerializedName("accepts_marketing")
    val acceptsMarketing: Boolean?,
    @SerializedName("accepts_marketing_updated_at")
    val acceptsMarketingUpdatedAt: String?,
    val addresses: List<Address>?,
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    val currency: String?,
    @SerializedName("default_address")
    val defaultAddress: Address?,
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    val id: Long?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("last_order_id")
    val lastOrderId: Any?,
    @SerializedName("last_order_name")
    val lastOrderName: Any?,
    @SerializedName("marketing_opt_in_level")
    val marketingOptInLevel: Any?,
    @SerializedName("multipass_identifier")
    val multipassIdentifier: Any?,
    val note: Any?,
    @SerializedName("orders_count")
    val ordersCount: Int?,
    val phone: String?,
    val state: String?,
    val tags: String?,
    @SerializedName("tax_exempt")
    val taxExempt: Boolean?,
    @SerializedName("tax_exemptions")
    val taxExemptions: List<Any>?,
    @SerializedName("total_spent")
    val totalSpent: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("verified_email")
    val verifiedEmail: Boolean?
) {
    constructor(firstName: String, email: String, pass: String) : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        email,
        firstName,
        null,
        null,
        null,
        null,
        null,
        null,
        pass,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    ) {
    }
}

