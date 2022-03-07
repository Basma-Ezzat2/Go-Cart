package com.example.gocart.ui.home.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.gocart.pojo.Product
import com.example.gocart.retrofit.RetrofitBuilder
import com.example.gocart.room.RoomDataBase
import com.example.gocart.room.RoomRepository
import com.example.gocart.ui.home.pojo.brands.Brands
import com.example.gocart.ui.home.pojo.product.ProductsModel
import com.example.gocart.ui.home.pojo.productdetail.Variants
import com.example.gocart.ui.home.pojo.search.Products
import com.example.gocart.ui.home.pojo.search.SearchProduct
import com.example.gocart.ui.home.repository.HomeRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var mylist: ArrayList<Products> = ArrayList()


    //    var api = RetrofitBuilder.retro.create(ApiService::class.java)
    private val homeRepository = HomeRepository(RetrofitBuilder.api)

    private val mutableResponse = MutableLiveData<Brands>()
    val liveDataResponse: LiveData<Brands> get() = mutableResponse

    private val productsMutable = MutableLiveData<ProductsModel>()
    val productsByBrand: LiveData<ProductsModel> get() = productsMutable

    val errorMutable = MutableLiveData<String>()

    val repo = RoomRepository(RoomDataBase.getInstance(application))


    private val productDetailsMutable = MutableLiveData<com.example.gocart.ui.home.pojo.productdetail.Product>()
    val productDetails: LiveData<com.example.gocart.ui.home.pojo.productdetail.Product> get() = productDetailsMutable

    private val varientsMutable = MutableLiveData<Variants>()
    val varients: LiveData<Variants> get() = varientsMutable

    private val productsSearchMutable = MutableLiveData<SearchProduct>()
    val productsSearch: LiveData<SearchProduct> get() = productsSearchMutable





    init {
        getAllBrands()
        getSearch()
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

    fun getProductByBrand(brandId: String?) {
        viewModelScope.launch {
            val products = homeRepository.getProduct(brandId!!)
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

    fun getSearch() {
        viewModelScope.launch {
            val products = homeRepository.getSearch()
            if (products.isSuccessful) {
                productsSearchMutable.postValue(products.body())
            } else {
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    fun getData(){
        viewModelScope.launch{
            val data= homeRepository.getSearch()
            val allProducts=data.body()
//            mylist.addAll(listOf(allProducts))

        }
    }

    fun searchAll( query : String){
        var word=mylist.filter {
            it!!.title.equals(query)

        }

    }

    private fun handleError(errorMsg: String) {
        errorMutable.postValue(errorMsg)
    }

    fun addToCart(product : Product) = viewModelScope.launch {
        repo.saveCartItem(product.toProductCartModule())
    }


}