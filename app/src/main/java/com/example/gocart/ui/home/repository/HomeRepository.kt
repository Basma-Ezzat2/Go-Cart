package com.example.gocart.ui.home.repository

import com.example.gocart.retrofit.ApiService



class HomeRepository(private val api: ApiService) {

    suspend fun getBrands() = api.getBrands()

    suspend fun getProductsByBrand(brandId: Long) = api.getProducts(brandId)

    suspend fun getProductDetails(ProductId: Long) = api.getProductDetails(ProductId)

    suspend fun getSearch()=api.getSearchProducts()



}