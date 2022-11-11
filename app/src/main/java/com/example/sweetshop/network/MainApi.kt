package com.example.sweetshop.network

import com.example.sweetshop.model.MainModel
import com.example.sweetshop.model.OrderModel
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MainApi {

    @GET("api-tri-choco/home.php")
    suspend fun getMainModel(): MainModel

    @FormUrlEncoded
    @POST("/bot5587769300:AAGWOLosPA-L6JNxEns1jQqH3ve49DzDQTY/sendMessage")
    suspend fun sendOrder(
        //@Path("bot_token") bot_token: String,
        @Field("chat_id") chat_id:String,
        @Field("text") text:String,
    ): Response<OrderModel>
}