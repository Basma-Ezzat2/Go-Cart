package com.example.gocart.ui.dashboard.repo

import com.example.gocart.retrofit.ApiService

class CategoryRepository(private val api: ApiService) {
    suspend fun getWomanProducts() = api.getWomenProductsList()
    suspend fun getKidsProducts() = api.getKidsProductsList()
    suspend fun getMenProducts() = api.getMenProductsList()
    suspend fun getOnSaleProducts() = api.getOnSaleProductsList()
}