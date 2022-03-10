package com.example.gocart.retrofit

import com.example.gocart.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Credentials

object RetrofitBuilder {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client())
            .build()
    }

    private fun client(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val credentials = Credentials.basic(Constants.apiKey, Constants.password)
        httpClient.addInterceptor{
            it.proceed( it.request().newBuilder().header("Authorization", credentials).build()
            )
        }
        return httpClient.build()
    }

    var apiService: ApiService = retrofit.create(ApiService::class.java)


}