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

    private val errorMutable = MutableLiveData<String>()

    private val repo = RoomRepository(RoomDataBase.getInstance(application))


    private val productDetailsMutable =
        MutableLiveData<com.example.gocart.ui.home.pojo.productdetail.Product>()
    val productDetails: LiveData<com.example.gocart.ui.home.pojo.productdetail.Product> get() = productDetailsMutable

    private val varientsMutable = MutableLiveData<Variants>()
    val varients: LiveData<Variants> get() = varientsMutable

    private val _allProducts = MutableLiveData<SearchProduct>()
    private val allProduct: LiveData<SearchProduct> get() = _allProducts

    private val _searchedProduct = MutableLiveData<ArrayList<Products>>()
    val searchedProduct: LiveData<ArrayList<Products>> get() = _searchedProduct

    val queryList = ArrayList<Products>()


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



                Log.d("ayaa", "getProductDetails: " + productDetails.raw().request().url())
            } else {
                Log.d("ayaa", "getProductDetails: " + productDetails.raw().request().url())
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    private fun getSearch() {
        viewModelScope.launch {
            val products = homeRepository.getSearch()
            if (products.isSuccessful) {
                _allProducts.postValue(products.body())
            } else {
                handleError("حدث خطا اثناء تحميل المنتجات")
            }
            cancel()
        }
    }

    /* fun getData() {
         viewModelScope.launch {
             val data = homeRepository.getSearch()
             val allProducts = data.body()
 //            mylist.addAll(listOf(allProducts))

         }
     }*/

    fun searchAll(query: String) {
        if (query.isEmpty()) {
            queryList.clear()
            _searchedProduct.postValue(queryList)
        } else {
            for (s in allProduct.value!!.products) {
                if (s.title!!.lowercase().contains(query.lowercase())) {
                    queryList.add(s)
                    _searchedProduct.postValue(queryList)
                }
            }
        }
    }

    private fun handleError(errorMsg: String) {
        errorMutable.postValue(errorMsg)
    }

    fun addToCart(product: Product) = viewModelScope.launch {
        repo.saveCartItem(product.toProductCartModule())
    }

    fun sortSearch(i: Int) {
        if (i == 1) {
            val arr = allProduct.value!!.products
//            for (io in 0 until arr.size)
                queryList.sortWith(Comparator { o1, o2 -> o1.vendor!!.compareTo(o2.vendor!!) })

            _searchedProduct.postValue(queryList)
        }
    }


}