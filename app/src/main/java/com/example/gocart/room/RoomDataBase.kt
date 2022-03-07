package com.example.gocart.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gocart.pojo.OrderObject
import com.example.gocart.pojo.Product
import com.example.gocart.pojo.ProductCartModule


@TypeConverters(Converter::class)
@Database(entities = [Product::class, ProductCartModule::class, OrderObject::class], version = 1,exportSchema = false)
abstract class RoomDataBase : RoomDatabase() {
    companion object{
        @Volatile
        private var db : RoomDataBase? =null

        fun getInstance(application: Application): RoomDataBase? {
            synchronized(this) {
                if (db == null)
                    db = Room.databaseBuilder(
                        application, RoomDataBase::class.java, "WishListDataBase"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return db
        }

    }

    abstract fun roomDAO(): RoomDAO

}