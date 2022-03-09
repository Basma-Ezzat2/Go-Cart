package com.example.gocart.ui.category


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.entity.categoriesPojo.ExampleJson2KtKotlin
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.dashboard.repo.CategoryRepository
import kotlinx.coroutines.cancel

import kotlinx.coroutines.launch


class CategoryViewModel : ViewModel() {

    private val categoryRepository = CategoryRepository(RetrofitBuilder.apiService)

    private val productResponse = MutableLiveData<ExampleJson2KtKotlin>()
    val liveDataResponse: LiveData<ExampleJson2KtKotlin> get() = productResponse
    private val menResponse = MutableLiveData<ExampleJson2KtKotlin>()
    val liveDataResponse2: LiveData<ExampleJson2KtKotlin> get() = menResponse
    private val kidsResponse = MutableLiveData<ExampleJson2KtKotlin>()
    val liveDataResponse3: LiveData<ExampleJson2KtKotlin> get() = kidsResponse
    private val salesResponse = MutableLiveData<ExampleJson2KtKotlin>()
    val liveDataResponse4: LiveData<ExampleJson2KtKotlin> get() = salesResponse
    private val errorMutable = MutableLiveData<String>()


    init {
        getWomanProductsList()
        getMenProductsList()
        getKidsProductsList()
        getOnSaleProductsList()


    }
    private fun getWomanProductsList() {
        viewModelScope.launch {
            val repo = categoryRepository.getWomanProducts()
            if (repo.isSuccessful){
                productResponse.postValue(repo.body())
            }
            cancel()
        }
    }
    private fun getKidsProductsList() {
        viewModelScope.launch {
            val repo = categoryRepository.getKidsProducts()
            if (repo.isSuccessful){
                kidsResponse.postValue(repo.body())
            }else {
                handleError("No data to show")
            }
            cancel()
        }
    }
    private fun getMenProductsList() {
        viewModelScope.launch {
            val repo = categoryRepository.getMenProducts()
            if (repo.isSuccessful){
                menResponse.postValue(repo.body())
            } else {
                handleError("No data to show")
            }
            cancel()
        }
    }
    private fun getOnSaleProductsList() {
        viewModelScope.launch {
            val repo = categoryRepository.getOnSaleProducts()
            if (repo.isSuccessful){
                salesResponse.postValue(repo.body())
            } else {
                handleError("No data to show")
            }
            cancel()
        }
    }
    private fun handleError(errorMsg: String) {
        errorMutable.postValue(errorMsg)
    }
}