package com.example.gocart.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gocart.pojo.OrderObject
import com.example.gocart.pojo.Product
import com.example.gocart.pojo.ProductCartModule


@Dao
interface RoomDAO {

    // *** CART ***

    @Query("SELECT * FROM Cart")
    fun getAllCartList(): LiveData<MutableList<ProductCartModule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCartItem(withItem: ProductCartModule)

    @Query("DELETE FROM Cart WHERE id LIKE:id")
    suspend fun deleteOneCartItem(id: Long)


    @Query("DELETE  FROM Cart")
    suspend fun deleteAllFromCart()


    // *** WISHLIST ***

    @Query("SELECT * FROM WishList LIMIT 4")
    fun getFourFromWishList(): LiveData<List<Product>>

    @Query("SELECT * FROM WishList")
    fun getAllWishList(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWishList(withItem: Product)

    @Query("DELETE FROM WishList WHERE id LIKE:id")
    suspend fun deleteOneWithItem(id: Long)

    @Query("DELETE FROM WishList")
    suspend fun deleteAllWishlist()

    @Query("SELECT * FROM WishList WHERE id LIKE:id LIMIT 1")
    fun getOneWithItem(id: Long) : LiveData<Product>

    // *** ORDER ***

    @Query("SELECT * FROM Orders")
    fun getAllOrderList(): LiveData<List<OrderObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrderList(orderItem: OrderObject)

    @Query("SELECT * FROM Orders LIMIT 2")
    fun getTwoFromOrderList(): LiveData<List<OrderObject>>

}