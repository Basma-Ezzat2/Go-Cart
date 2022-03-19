package com.example.gocart.room


import androidx.room.TypeConverter
import com.example.gocart.pojo.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromJsonToAddress(gson: String): Address? {
        val type = object : TypeToken<Address>() {}.type
        return Gson().fromJson(gson, type)
    }

    @TypeConverter
    fun fromAddressToGeson(list: Address?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromVariantToGeson(list: List<Variants>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToVariant(gson: String): List<Variants>? {
        val type = object : TypeToken<List<Variants>>() {}.type
        return Gson().fromJson(gson, type)
    }


    @TypeConverter
    fun fromOptionsToGeson(list: List<Options>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonTOptions(gson: String): List<Options> {
        val type = object : TypeToken<List<Variants>>() {}.type
        return Gson().fromJson(gson, type)
    }


    @TypeConverter
    fun fromImagesToGeson(list: List<Images>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonTOImages(gson: String): List<Images> {
        val type = object : TypeToken<List<Variants>>() {}.type
        return Gson().fromJson(gson, type)
    }


    @TypeConverter
    fun fromImageToGeson(app: Image): String = Gson().toJson(app)

    @TypeConverter
    fun fromJsonTOImage(string: String): Image = Gson().fromJson(string, Image::class.java)


}