package com.example.sweetshop.network

import com.example.sweetshop.model.MainModel
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {

    @GET("api-tri-choco/home.php")
    suspend fun getMainModel(): MainModel
}