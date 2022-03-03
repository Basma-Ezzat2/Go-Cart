package com.example.gocart.retrofit

import com.example.gocart.ui.home.pojo.brands.Brands
import com.example.gocart.ui.home.pojo.product.ProductsModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("smart_collections.json")
    suspend fun getBrands(): Response<Brands>

    @GET("collections/{collection_id}/products.json")
    suspend fun getProducts(@Path("collection_id") collectionId: Long): Response<ProductsModel>




   /* @GET("products/{product_id}.json")
    suspend fun getProductDetails(@Path("product_id") product_id:Long): Response<>
*/

    @GET("products.json?")
    suspend fun getProductsByVendor(@Query("vendor") vendor: String): Response<ProductsModel>

    @GET("products.json?")
    suspend fun getProductsFromType(@Query("product_type") productType: String): Response<ProductsModel>



//    @GET("products/{productID}/images.json")
//    suspend fun getProduct(@Path("productID") ProductId:Long ):
//            Response<Product>
//
//    @GET("products/{productID}/images.json")
//    suspend fun getProductImage(@Path("productID") ProductId:Long ):
//            Response<ProductImages>

//    @POST("price_rules.json")
//    suspend fun createDiscount(@Body priceRule:Discount):
//            Response<Discount>
//
//    @GET("price_rules/{id}.json")
//    suspend fun getDiscount(@Path("id") discountId:Long ):
//            Response<Discount>

}