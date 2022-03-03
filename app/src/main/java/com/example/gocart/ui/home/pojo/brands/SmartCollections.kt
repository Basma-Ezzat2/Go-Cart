package com.example.gocart.ui.home.pojo.brands

import com.google.gson.annotations.SerializedName

data class SmartCollections (
    @SerializedName("id"                   ) var id                : Long?             = null,
    @SerializedName("handle"               ) var handle            : String?          = null,
    @SerializedName("title"                ) var title             : String?          = null,
    @SerializedName("updated_at"           ) var updatedAt         : String?          = null,
    @SerializedName("body_html"            ) var bodyHtml          : String?          = null,
    @SerializedName("published_at"         ) var publishedAt       : String?          = null,
    @SerializedName("sort_order"           ) var sortOrder         : String?          = null,
    @SerializedName("template_suffix"      ) var templateSuffix    : String?          = null,
    @SerializedName("disjunctive"          ) var disjunctive       : Boolean?         = null,
    @SerializedName("rules"                ) var rules             : ArrayList<Rules> = arrayListOf(),
    @SerializedName("published_scope"      ) var publishedScope    : String?          = null,
    @SerializedName("admin_graphql_api_id" ) var adminGraphqlApiId : String?          = null,
    @SerializedName("image"                ) var image             : Image?           = Image()
)