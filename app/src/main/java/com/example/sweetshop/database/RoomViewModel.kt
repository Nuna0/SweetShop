package com.example.sweetshop.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class RoomViewModel (application: Application): AndroidViewModel(application){
    val getAllTasks: LiveData<List<ProductsInBasket>>
    private val repository: RoomRepository

    init {
        val roomDao = RoomDataBase.getDatabase(application).roomDao()
        repository = RoomRepository(roomDao)
        getAllTasks = repository.getAllTasks
    }

    fun  addTask(tasks: ProductsInBasket){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTasks(tasks)
        }
    }

    /*fun updateTask(tasks: ProductsInBasket){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(tasks)
        }
    }*/

    fun deleteTask(tasks: ProductsInBasket){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(tasks)
        }
    }

}