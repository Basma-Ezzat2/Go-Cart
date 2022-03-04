package com.example.gocart.ui.dashboard.repo

import com.example.gocart.retrofit.ApiService

class CategoryRepository(private val api: ApiService) {
    suspend fun getWomanProductsList() = api.getWomanProductsList()
//    suspend fun getKidsProductsList() = api.getKidsProductsList()
//    suspend fun getMenProductsList() = api.getMenProductsList()
//    suspend fun getOnSaleProductsList() = api.getOnSaleProductsList()
}