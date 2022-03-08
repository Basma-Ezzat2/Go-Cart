package com.example.gocart.retrofit

import com.example.gocart.auth.pojo.CustomerModel
import com.example.gocart.auth.pojo.EditCustomerModel
import com.example.gocart.ui.home.pojo.brands.Brands
import com.example.gocart.ui.home.pojo.product.ProductsModel
import com.example.gocart.data.entity.collection.ProductsList
import com.example.gocart.ui.home.pojo.productdetail.ProductDetails
import com.example.gocart.ui.home.pojo.search.SearchProduct
import com.stash.shopeklobek.model.entities.CustomerLoginModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //Auth
    @POST("customers.json")
    suspend fun register(@Body customer: CustomerModel):
            Response<CustomerModel>

    @GET("customers.json")
    suspend fun login(): Response<CustomerLoginModel>

    @PUT("customers/{id}.json")
    suspend fun updateCustomer(@Path("id") customerId:Long,
                               @Body customer: EditCustomerModel
    ):
            Response<EditCustomerModel>


    @GET("smart_collections.json")
    suspend fun getBrands(): Response<Brands>

    @GET("collections/{collection_id}/products.json")
    suspend fun getProducts(@Path("collection_id") collectionId: Long): Response<ProductsModel>

    @GET("products/{product_id}.json")
    suspend fun getProductDetails(@Path("product_id") product_id:Long): Response<ProductDetails>

    @GET("products.json")
    suspend fun getSearchProducts() : Response<SearchProduct>



//main categories api
    @GET("collections/398034600167/products.json")
    fun getWomanProductsList(): Response<ProductsList>

//    @GET("collections/268359663814/products.json")
//    fun getKidsProductsList(): Call<ProductsList>
//
//    @GET("collections/268359598278/products.json")
//    fun getMenProductsList(): Call<ProductsList>
//
//    @GET("collections/268359696582/products.json")
//    fun getOnSaleProductsList(): Call<ProductsList>
//
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