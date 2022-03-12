package com.example.gocart.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.gocart.pojo.OrderObject
import com.example.gocart.pojo.Product
import com.example.gocart.pojo.ProductCartModule

class RoomRepository (roomDatabase: RoomDataBase?){

val roomDao : RoomDAO = roomDatabase!!.roomDAO()


    //CART

    fun getAllCartList(): LiveData<MutableList<ProductCartModule>> {
        return roomDao.getAllCartList()
    }

    suspend fun saveCartItem(withItem: ProductCartModule) {
        roomDao.saveCartItem(withItem)
    }

    suspend fun deleteOnCartItem(id: Long) {
        roomDao.deleteOneCartItem(id)
    }

    suspend fun deleteAllFromCart() {
        roomDao.deleteAllFromCart()
    }





    // ORDER

    fun getTwoFromOrderList(): LiveData<List<OrderObject>> {
        return  roomDao.getTwoFromOrderList()
    }

    fun getAllOrderList(): LiveData<List<OrderObject>> {
        return  roomDao.getAllOrderList()
    }

    suspend fun saveOrderList(orderItem: OrderObject) {
        roomDao.saveOrderList(orderItem)
    }




    // wish list
    fun getFourFromWishList(): LiveData<List<Product>> {
        return roomDao.getFourFromWishList()
    }

    fun getAllWishList(): LiveData<List<Product>> {
        return roomDao.getAllWishList()
    }

    suspend fun saveWishList(withItem: Product) {
        roomDao.saveWishList(withItem)
    }

    suspend fun deleteOneWishItem(id: Long) {
        roomDao.deleteOneWithItem(id)
    }

    fun getOneWishItem(id: Long): LiveData<Product> {
        return roomDao.getOneWithItem(id)
    }

    suspend fun deleteAllFromWishlist() {
        roomDao.deleteAllWishlist()
    }

}