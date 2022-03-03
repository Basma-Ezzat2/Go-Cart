package com.example.gocart.retrofit

import com.example.gocart.BuildConfig
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
    private lateinit var retrofit: Retrofit

     val retro: Retrofit by lazy {
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(buildAuthClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit
    }

    var api = retro.create(ApiService::class.java)

    private fun authInterceptor(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(Constants.apiKey, Constants.password);
        return chain.proceed(
            chain.request().newBuilder().header("Authorization", credentials)
                .build()
        )
    }

    private fun buildAuthClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(provideLoggingInterceptor())
            .addInterceptor(::authInterceptor)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        return httpClient

            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()

        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return interceptor
    }
}