package com.example.sweetshop.repository

import com.example.sweetshop.model.*
import com.example.sweetshop.network.RetrofitInstance
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class Repository {


    suspend fun getMainModel(): MainModel {
        return RetrofitInstance.api.getMainModel()
    }

    suspend fun sendOrder(/*bot_token:String,*/ chat_id: String,text:String,): Response<OrderModel> {
        return RetrofitInstance.apiTgBot.sendOrder(/*bot_token,*/ chat_id,text)
    }

    /*suspend fun getCategories(): ArrayList<Categories> {
        val list = arrayListOf<Categories>()
        arrayListOf(RetrofitInstance.api.getMainModel()).map {
            list.addAll(it.categories)
        }
        return list
    }

    suspend fun getCatalog(): ArrayList<Catalog> {
        val list = arrayListOf<Catalog>()
        arrayListOf(RetrofitInstance.api.getMainModel()).map {
            list.addAll(it.catalog)
        }
        return list
    }*/


    /*suspend fun getProducts(): ArrayList<Products> {
        val list = arrayListOf<Products>()
        arrayListOf(RetrofitInstance.api.getMainModel()).map {
            list.addAll(it.catalog.forEach {
                it.products
            })
        }
        return list
    }*/

}