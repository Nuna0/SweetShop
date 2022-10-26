package com.example.sweetshop.database

import androidx.lifecycle.LiveData

class RoomRepository (private val roomDao: RoomDao){

    val getAllTasks: LiveData<List<ProductsInBasket>> = roomDao.getAllTasks()

    suspend fun addTasks(tasks: ProductsInBasket){
        roomDao.addProduct(tasks)
    }

    /*suspend fun updateTask(tasks: ProductsInBasket){
        roomDao.updateTask(tasks)
    }*/

    suspend fun deleteTask(tasks: ProductsInBasket){
        roomDao.deleteProduct(tasks)
    }

}