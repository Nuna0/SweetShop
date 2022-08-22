package com.example.sweetshop.repository

import com.example.sweetshop.model.Catalog
import com.example.sweetshop.model.Categories
import com.example.sweetshop.model.MainModel
import com.example.sweetshop.model.Products
import com.example.sweetshop.network.RetrofitInstance
import retrofit2.Response

class Repository {


    suspend fun getMainModel(): MainModel {
        return RetrofitInstance.api.getMainModel()
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