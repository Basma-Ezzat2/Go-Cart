package com.example.gocart.pojo

import java.io.Serializable

data class GetOrders(
    val orders: List<Order?>?
) {

    data class Order(
        val admin_graphql_api_id: String?,
        val app_id: Int?,
        val billing_address: BillingAddress?,
        val browser_ip: Any?,
        val buyer_accepts_marketing: Boolean?,
        val cancel_reason: Any?,
        val cancelled_at: Any?,
        val cart_token: Any?,
        val checkout_id: Any?,
        val checkout_token: Any?,
        val closed_at: Any?,
        val confirmed: Boolean?,
        val contact_email: String?,
        val created_at: String?,
        val currency: String?,
        val current_subtotal_price: String?,
        val current_subtotal_price_set: CurrentSubtotalPriceSet?,
        val current_total_discounts: String?,
        val current_total_discounts_set: CurrentTotalDiscountsSet?,
        val current_total_duties_set: Any?,
        val current_total_price: String?,
        val current_total_price_set: CurrentTotalPriceSet?,
        val current_total_tax: String?,
        val current_total_tax_set: CurrentTotalTaxSet?,
        val customer: Customer?,
        val customer_locale: Any?,
        val device_id: Any?,
        val discount_applications: List<DiscountApplication?>?,
        val discount_codes: List<DiscountCodes>?,
        val email: String?,
        val financial_status: String?,
        val fulfillment_status: Any?,
        val fulfillments: List<Fulfillment?>?,
        val gateway: String?,
        val id: Long?,
        val landing_site: Any?,
        val landing_site_ref: Any?,
        val line_items: List<LineItem?>?,
        val location_id: Any?,
        val name: String?,
        val note: Any?,
        val note_attributes: List<Any?>?,
        val number: Int?,
        val order_number: Int?,
        val order_status_url: String?,
        val original_total_duties_set: Any?,
        val payment_gateway_names: List<String?>?,
        val phone: Any?,
        val presentment_currency: String?,
        val processed_at: String?,
        val processing_method: String?,
        val reference: Any?,
        val referring_site: Any?,
        val refunds: List<Refund?>?,
        val shipping_address: ShippingAddress?,
        val shipping_lines: List<Any?>?,
        val source_identifier: Any?,
        val source_name: String?,
        val source_url: Any?,
        val subtotal_price: String?,
        val subtotal_price_set: SubtotalPriceSet?,
        val tags: String?,
        val tax_lines: List<Any?>?,
        val taxes_included: Boolean?,
        val test: Boolean?,
        val token: String?,
        val total_discounts: String?,
        val total_discounts_set: TotalDiscountsSet?,
        val total_line_items_price: String?,
        val total_line_items_price_set: TotalLineItemsPriceSet?,
        val total_outstanding: String?,
        val total_price: String?,
        val total_price_set: TotalPriceSet?,
        val total_price_usd: String?,
        val total_shipping_price_set: TotalShippingPriceSet?,
        val total_tax: String?,
        val total_tax_set: TotalTaxSet?,
        val total_tip_received: String?,
        val total_weight: Int?,
        val updated_at: String?,
        val user_id: Any?
    ) : Serializable{

        data class BillingAddress (
            val address1: String?,
            val address2: Any?,
            val city: String?,
            val company: Any?,
            val country: String?,
            val country_code: String?,
            val first_name: String?,
            val last_name: String?,
            val latitude: Any?,
            val longitude: Any?,
            val name: String?,
            val phone: Any?,
            val province: Any?,
            val province_code: Any?,
            val zip: Any?
        )  : Serializable

        data class CurrentSubtotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class CurrentTotalDiscountsSet (
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class CurrentTotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        )  : Serializable{
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class CurrentTotalTaxSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        )  : Serializable{
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class Customer(
            val accepts_marketing: Boolean?,
            val accepts_marketing_updated_at: String?,
            val admin_graphql_api_id: String?,
            val created_at: String?,
            val currency: String?,
            val default_address: DefaultAddress?,
            val email: String?,
            val first_name: String?,
            val id: Long?,
            val last_name: Any?,
            val last_order_id: Long?,
            val last_order_name: String?,
            val marketing_opt_in_level: Any?,
            val multipass_identifier: Any?,
            val note: String?,
            val orders_count: Int?,
            val phone: Any?,
            val state: String?,
            val tags: String?,
            val tax_exempt: Boolean?,
            val tax_exemptions: List<Any?>?,
            val total_spent: String?,
            val updated_at: String?,
            val verified_email: Boolean?
        )  : Serializable{
            data class DefaultAddress(
                val address1: String?,
                val address2: String?,
                val city: String?,
                val company: String?,
                val country: String?,
                val country_code: String?,
                val country_name: String?,
                val customer_id: Long?,
                val default: Boolean?,
                val first_name: String?,
                val id: Long?,
                val last_name: String?,
                val name: String?,
                val phone: String?,
                val province: String?,
                val province_code: String?,
                val zip: String?
            ) : Serializable
        }

        data class DiscountApplication(
            val allocation_method: String?,
            val code: String?,
            val description: String?,
            val target_selection: String?,
            val target_type: String?,
            val title: String?,
            val type: String?,
            val value: String?,
            val value_type: String?
        ) : Serializable

        data class DiscountCode(
            val amount: String?,
            val code: String?
        ) : Serializable

        data class Fulfillment(
            val admin_graphql_api_id: String?,
            val created_at: String?,
            val id: Long,
            val line_items: List<LineItem?>?,
            val location_id: Long?,
            val name: String?,
            val order_id: Long?,
            val receipt: Receipt?,
            val service: String?,
            val shipment_status: Any?,
            val status: String?,
            val tracking_company: Any?,
            val tracking_number: Any?,
            val tracking_numbers: List<Any?>?,
            val tracking_url: Any?,
            val tracking_urls: List<Any?>?,
            val updated_at: String?
        ) : Serializable {
            data class LineItem(
                val admin_graphql_api_id: String?,
                val discount_allocations: List<Any?>?,
                val duties: List<Any?>?,
                val fulfillable_quantity: Int?,
                val fulfillment_service: String?,
                val fulfillment_status: String?,
                val gift_card: Boolean?,
                val grams: Int?,
                val id: Long?,
                val name: String?,
                val price: String?,
                val price_set: PriceSet?,
                val product_exists: Boolean?,
                val product_id: Long?,
                val properties: List<Any?>?,
                val quantity: Int?,
                val requires_shipping: Boolean?,
                val sku: String?,
                val tax_lines: List<Any?>?,
                val taxable: Boolean?,
                val title: String?,
                val total_discount: String?,
                val total_discount_set: TotalDiscountSet?,
                val variant_id: Long?,
                val variant_inventory_management: String?,
                val variant_title: String?,
                val vendor: String?
            ) : Serializable {
                data class PriceSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) : Serializable {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable
                }

                data class TotalDiscountSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) : Serializable {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable
                }
            }

            class Receipt(
            ) : Serializable
        }

        data class LineItem(
            val admin_graphql_api_id: String?,
            val discount_allocations: List<DiscountAllocation?>?,
            val duties: List<Any?>?,
            val fulfillable_quantity: Int?,
            val fulfillment_service: String?,
            val fulfillment_status: Any?,
            val gift_card: Boolean?,
            val grams: Int?,
            val id: Long,
            val name: String?,
            val price: String?,
            val price_set: PriceSet?,
            val product_exists: Boolean?,
            val product_id: Long?,
            val properties: List<Any?>?,
            val quantity: Int?,
            val requires_shipping: Boolean?,
            val sku: String?,
            val tax_lines: List<Any?>?,
            val taxable: Boolean?,
            val title: String?,
            val total_discount: String?,
            val total_discount_set: TotalDiscountSet?,
            val variant_id: Long?,
            val variant_inventory_management: String?,
            val variant_title: String?,
            val vendor: String?
        ) : Serializable {
            data class DiscountAllocation(
                val amount: String?,
                val amount_set: AmountSet?,
                val discount_application_index: Int?
            )  : Serializable{
                data class AmountSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) : Serializable {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable
                }
            }

            data class PriceSet(
                val presentment_money: PresentmentMoney?,
                val shop_money: ShopMoney?
            ) : Serializable {
                data class PresentmentMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable

                data class ShopMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable
            }

            data class TotalDiscountSet(
                val presentment_money: PresentmentMoney?,
                val shop_money: ShopMoney?
            ) : Serializable {
                data class PresentmentMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable

                data class ShopMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable
            }
        }

        data class Refund(
            val admin_graphql_api_id: String?,
            val created_at: String?,
            val duties: List<Any?>?,
            val id: Long?,
            val note: Any?,
            val order_adjustments: List<OrderAdjustment?>?,
            val order_id: Long?,
            val processed_at: String?,
            val refund_line_items: List<Any?>?,
            val restock: Boolean?,
            val total_duties_set: TotalDutiesSet?,
            val transactions: List<Transaction?>?,
            val user_id: Any?
        ) : Serializable {
            data class OrderAdjustment(
                val amount: String?,
                val amount_set: AmountSet?,
                val id: Long?,
                val kind: String?,
                val order_id: Long?,
                val reason: String?,
                val refund_id: Long?,
                val tax_amount: String?,
                val tax_amount_set: TaxAmountSet?
            )  : Serializable{
                data class AmountSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) : Serializable {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable
                }

                data class TaxAmountSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) : Serializable {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    ) : Serializable
                }
            }

            data class TotalDutiesSet(
                val presentment_money: PresentmentMoney?,
                val shop_money: ShopMoney?
            )  : Serializable{
                data class PresentmentMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable

                data class ShopMoney(
                    val amount: String?,
                    val currency_code: String?
                ) : Serializable
            }

            data class Transaction(
                val admin_graphql_api_id: String?,
                val amount: String?,
                val authorization: Any?,
                val created_at: String?,
                val currency: String?,
                val device_id: Any?,
                val error_code: Any?,
                val gateway: String?,
                val id: Long?,
                val kind: String?,
                val location_id: Any?,
                val message: Any?,
                val order_id: Long?,
                val parent_id: Any?,
                val processed_at: String?,
                val receipt: Receipt?,
                val source_name: String?,
                val status: String?,
                val test: Boolean?,
                val user_id: Any?
            ) : Serializable {
                class Receipt(
                ) : Serializable
            }
        }

        data class ShippingAddress(
            val address1: String?,
            val address2: Any?,
            val city: String?,
            val company: Any?,
            val country: String?,
            val country_code: String?,
            val first_name: String?,
            val last_name: String?,
            val latitude: Any?,
            val longitude: Any?,
            val name: String?,
            val phone: Any?,
            val province: Any?,
            val province_code: Any?,
            val zip: Any?
        ) : Serializable

        data class SubtotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class TotalDiscountsSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class TotalLineItemsPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        )  : Serializable{
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class TotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class TotalShippingPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) : Serializable {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }

        data class TotalTaxSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        )  : Serializable{
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            ) : Serializable
        }
    }
}