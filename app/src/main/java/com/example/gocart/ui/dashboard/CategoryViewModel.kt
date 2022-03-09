package com.example.gocart.ui.dashboard


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.entity.collection.ProductsList
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.dashboard.repo.CategoryRepository
import kotlinx.coroutines.cancel

import kotlinx.coroutines.launch


class CategoryViewModel : ViewModel() {

    private val categoryRepository = CategoryRepository(RetrofitBuilder.apiService)

    private val productResponse = MutableLiveData<ProductsList>()
    val liveDataResponse: LiveData<ProductsList> get() = productResponse


    private fun getWomanProductsList() {
        viewModelScope.launch {
            val repo = categoryRepository.getWomanProductsList()
            if (repo.isSuccessful){
                productResponse.postValue(repo.body())
            }
            cancel()

        }
    }
}