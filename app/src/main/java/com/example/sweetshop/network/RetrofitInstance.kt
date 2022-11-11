package com.example.sweetshop.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {

    val clientSetup = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
        .build()

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientSetup)
            .build()
    }

    private val retrofitTgBot by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.TG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientSetup)
            .build()
    }


    val api: MainApi by lazy {
        retrofit.create(MainApi::class.java)
    }

    val apiTgBot: MainApi by lazy {
        retrofitTgBot.create(MainApi::class.java)
    }
}