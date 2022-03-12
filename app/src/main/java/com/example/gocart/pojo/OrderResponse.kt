package com.example.gocart.pojo


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    val order: OrderX?
)
data class OrdersGetResponse(
    val orders: List<OrderX>?
)
data class OrdersResponse(
    val response: OrdersGetResponse
)
data class OrderX(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String?,
    @SerializedName("app_id")
    val appId: Int?,
    @SerializedName("browser_ip")
    val browserIp: Any?,
    @SerializedName("buyer_accepts_marketing")
    val buyerAcceptsMarketing: Boolean?,
    @SerializedName("cancel_reason")
    val cancelReason: Any?,
    @SerializedName("cancelled_at")
    val cancelledAt: Any?,
    @SerializedName("cart_token")
    val cartToken: Any?,
    @SerializedName("checkout_id")
    val checkoutId: Any?,
    @SerializedName("checkout_token")
    val checkoutToken: Any?,
    @SerializedName("closed_at")
    val closedAt: Any?,
    val confirmed: Boolean?,
    @SerializedName("contact_email")
    val contactEmail: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    val currency: String?,
    @SerializedName("current_subtotal_price")
    val currentSubtotalPrice: String?,
    @SerializedName("current_subtotal_price_set")
    val currentSubtotalPriceSet: PriceSet?,
    @SerializedName("current_total_discounts")
    val currentTotalDiscounts: String?,
    @SerializedName("current_total_discounts_set")
    val currentTotalDiscountsSet: PriceSet?,
    @SerializedName("current_total_duties_set")
    val currentTotalDutiesSet: Any?,
    @SerializedName("current_total_price")
    val currentTotalPrice: String?,
    @SerializedName("current_total_price_set")
    val currentTotalPriceSet: PriceSet?,
    @SerializedName("current_total_tax")
    val currentTotalTax: String?,
    @SerializedName("current_total_tax_set")
    val currentTotalTaxSet: PriceSet?,
    val customer: Customer?,
    @SerializedName("customer_locale")
    val customerLocale: Any?,
    @SerializedName("device_id")
    val deviceId: Any?,
    @SerializedName("discount_applications")
    val discountApplications: List<Any>?,
    @SerializedName("discount_codes")
    val discountCodes: List<Any>?,
    val email: String?,
    @SerializedName("financial_status")
    val financialStatus: String?,
    @SerializedName("fulfillment_status")
    val fulfillmentStatus: Any?,
    val fulfillments: List<Any>?,
    val gateway: String?,
    val id: Long?,
    @SerializedName("landing_site")
    val landingSite: Any?,
    @SerializedName("landing_site_ref")
    val landingSiteRef: Any?,
    @SerializedName("line_items")
    val lineItems: List<LineItemX>?,
    @SerializedName("location_id")
    val locationId: Any?,
    val name: String?,
    val note: Any?,
    @SerializedName("note_attributes")
    val noteAttributes: List<Any>?,
    val number: Int?,
    @SerializedName("order_number")
    val orderNumber: Int?,
    @SerializedName("order_status_url")
    val orderStatusUrl: String?,
    @SerializedName("original_total_duties_set")
    val originalTotalDutiesSet: Any?,
    @SerializedName("payment_gateway_names")
    val paymentGatewayNames: List<Any>?,
    val phone: Any?,
    @SerializedName("presentment_currency")
    val presentmentCurrency: String?,
    @SerializedName("processed_at")
    val processedAt: String?,
    @SerializedName("processing_method")
    val processingMethod: String?,
    val reference: Any?,
    @SerializedName("referring_site")
    val referringSite: Any?,
    val refunds: List<Any>?,
    @SerializedName("shipping_lines")
    val shippingLines: List<Any>?,
    @SerializedName("source_identifier")
    val sourceIdentifier: Any?,
    @SerializedName("source_name")
    val sourceName: String?,
    @SerializedName("source_url")
    val sourceUrl: Any?,
    @SerializedName("subtotal_price")
    val subtotalPrice: String?,
    @SerializedName("subtotal_price_set")
    val subtotalPriceSet: PriceSet?,
    val tags: String?,
    @SerializedName("tax_lines")
    val taxLines: List<Any>?,
    @SerializedName("taxes_included")
    val taxesIncluded: Boolean?,
    val test: Boolean?,
    val token: String?,
    @SerializedName("total_discounts")
    val totalDiscounts: String?,
    @SerializedName("total_discounts_set")
    val totalDiscountsSet: PriceSet?,
    @SerializedName("total_line_items_price")
    val totalLineItemsPrice: String?,
    @SerializedName("total_line_items_price_set")
    val totalLineItemsPriceSet: PriceSet?,
    @SerializedName("total_outstanding")
    val totalOutstanding: String?,
    @SerializedName("total_price")
    val totalPrice: String?,
    @SerializedName("total_price_set")
    val totalPriceSet: PriceSet?,
    @SerializedName("total_price_usd")
    val totalPriceUsd: String?,
    @SerializedName("total_shipping_price_set")
    val totalShippingPriceSet: PriceSet?,
    @SerializedName("total_tax")
    val totalTax: String?,
    @SerializedName("total_tax_set")
    val totalTaxSet: PriceSet?,
    @SerializedName("total_tip_received")
    val totalTipReceived: String?,
    @SerializedName("total_weight")
    val totalWeight: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_id")
    val userId: Any?
)
data class LineItemX(
    @SerializedName("admin_graphql_api_id")
    val adminGraphqlApiId: String?,
    @SerializedName("discount_allocations")
    val discountAllocations: List<Any>?,
    val duties: List<Any>?,
    @SerializedName("fulfillable_quantity")
    val fulfillableQuantity: Int?,
    @SerializedName("fulfillment_service")
    val fulfillmentService: String?,
    @SerializedName("fulfillment_status")
    val fulfillmentStatus: Any?,
    @SerializedName("gift_card")
    val giftCard: Boolean?,
    val grams: Int?,
    val id: Long?,
    val name: String?,
    val price: String?,
    @SerializedName("price_set")
    val priceSet: PriceSet?,
    @SerializedName("product_exists")
    val productExists: Boolean?,
    @SerializedName("product_id")
    val productId: Long?,
    val properties: List<Any>?,
    val quantity: Int?,
    @SerializedName("requires_shipping")
    val requiresShipping: Boolean?,
    val sku: String?,
    @SerializedName("tax_lines")
    val taxLines: List<Any>?,
    val taxable: Boolean?,
    val title: String?,
    @SerializedName("total_discount")
    val totalDiscount: String?,
    @SerializedName("total_discount_set")
    val totalDiscountSet: PriceSet?,
    @SerializedName("variant_id")
    val variantId: Long?,
    @SerializedName("variant_inventory_management")
    val variantInventoryManagement: String?,
    @SerializedName("variant_title")
    val variantTitle: String?,
    val vendor: String?
)
data class PriceSet(
    @SerializedName("presentment_money")
    val presentmentMoney: PresentmentMoney?,
    @SerializedName("shop_money")
    val shopMoney: ShopMoney?
)
data class ShopMoney(
    val amount: String?,
    @SerializedName("currency_code")
    val currencyCode: String?
)
data class PresentmentMoney(
    val amount: String?,
    @SerializedName("currency_code")
    val currencyCode: String?
)