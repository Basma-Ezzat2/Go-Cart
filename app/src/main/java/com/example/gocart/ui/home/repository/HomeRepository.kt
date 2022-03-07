package com.example.gocart.ui.home.repository

import com.example.gocart.retrofit.ApiService
import com.example.gocart.ui.home.pojo.product.ProductsModel
import retrofit2.Response


class HomeRepository(private val api: ApiService) {

    suspend fun getBrands() = api.getBrands()

<<<<<<< Updated upstream
    suspend fun getProductsByBrand(brandId: Long) = api.getProducts(brandId)

    suspend fun getProductDetails(ProductId: Long) = api.getProductDetails(ProductId)

    suspend fun getSearch()=api.getSearchProducts()

=======
    suspend fun getProduct(vendor : String) = api.getProductsByVendor(vendor)
>>>>>>> Stashed changes

    suspend fun getProductDetails(ProductId: Long) = api.getProductDetails(ProductId)

    suspend fun getSearch()=api.getSearchProducts()


}