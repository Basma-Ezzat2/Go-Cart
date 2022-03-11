package com.example.gocart.retrofit

import androidx.viewbinding.BuildConfig
//import com.example.gocart.BuildConfig
import com.example.gocart.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.Credentials
import okhttp3.Response

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