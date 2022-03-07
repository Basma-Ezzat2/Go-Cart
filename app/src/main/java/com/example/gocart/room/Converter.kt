package com.example.gocart.room


import androidx.room.TypeConverter
import com.example.gocart.pojo.Image
import com.example.gocart.pojo.Images
import com.example.gocart.pojo.Options
import com.example.gocart.pojo.Variants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

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