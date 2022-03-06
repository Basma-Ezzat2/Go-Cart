package com.example.gocart.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.ui.home.pojo.brands.Brands
import com.example.gocart.ui.home.pojo.product.ProductsModel
import com.example.gocart.ui.home.pojo.productdetail.Product
import com.example.gocart.ui.home.pojo.productdetail.ProductDetails
import com.example.gocart.ui.home.pojo.productdetail.Variants
import com.example.gocart.ui.home.repository.HomeRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    //    var api = RetrofitBuilder.retro.create(ApiService::class.java)
    private val homeRepository = HomeRepository(RetrofitBuilder.api)

    private val mutableResponse = MutableLiveData<Brands>()
    val liveDataResponse: LiveData<Brands> get() = mutableResponse

    private val productsMutable = MutableLiveData<ProductsModel>()
    val productsByBrand: LiveData<ProductsModel> get() = productsMutable

    private val productDetailsMutable = MutableLiveData<Product>()
    val productDetails: LiveData<Product> get() = productDetailsMutable

    private val varientsMutable = MutableLiveData<Variants>()
    val varients: LiveData<Variants> get() = varientsMutable


    val errorMutable = MutableLiveData<String>()

    init {
        getAllBrands()
    }

    private fun getAllBrands() {
        viewModelScope.launch {
            val repo = homeRepository.getBrands()
            if (repo.isSuccessful) {
                mutableResponse.postValue(repo.body())

                // Log.d("ayaaa333", "HomeViewModel: Sucess " )
            } else {
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    fun getProductByBrand(brandId: Long?) {
        viewModelScope.launch {
            val products = homeRepository.getProductsByBrand(brandId!!)
            if (products.isSuccessful) {
                productsMutable.postValue(products.body())
            } else {
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    fun getProductDetails(ProductId: Long?) {
        viewModelScope.launch {
            val productDetails = homeRepository.getProductDetails(ProductId!!)
            if (productDetails.isSuccessful) {
                productDetailsMutable.postValue(productDetails.body()!!.product!!)
                productDetails.body()!!.product?.variants!!.forEach {
                    varientsMutable.postValue(it)
                }



                Log.d("ayaa", "getProductDetails: "+productDetails.raw().request().url())
            } else {
                Log.d("ayaa", "getProductDetails: "+productDetails.raw().request().url())
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    private fun handleError(errorMsg: String) {
        errorMutable.postValue(errorMsg)
    }

}