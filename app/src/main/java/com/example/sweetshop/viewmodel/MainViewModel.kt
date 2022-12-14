package com.example.sweetshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweetshop.model.Catalog
import com.example.sweetshop.model.Categories
import com.example.sweetshop.model.MainModel
import com.example.sweetshop.model.OrderModel
import com.example.sweetshop.repository.Repository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class MainViewModel ( private val repository: Repository): ViewModel() {

    val mainResponse: MutableLiveData<MainModel> = MutableLiveData()
    val sendOrderResponse: MutableLiveData<Response<OrderModel>> = MutableLiveData()
    val catalogResponse: MutableLiveData<ArrayList<Catalog>> = MutableLiveData()
    val categoriesResponse: MutableLiveData<ArrayList<Categories>> = MutableLiveData()

    fun getMainModel(){
        viewModelScope.launch {
            val response = repository.getMainModel()
            mainResponse.value = response
        }
    }

    fun sendOrder(/*bot_token:String,*/  chat_id: String,text:String){
        viewModelScope.launch {
            val response = repository.sendOrder(/*bot_token,*/chat_id, text)
            sendOrderResponse.value = response
        }
    }

   /* fun getCatalog(){
        viewModelScope.launch {
            val response = repository.getCatalog()
            catalogResponse.value = response
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            val response = repository.getCategories()
            categoriesResponse.value = response
        }
    }*/
}