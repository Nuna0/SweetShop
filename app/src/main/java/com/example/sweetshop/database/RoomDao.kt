package com.example.sweetshop.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(vararg product: ProductsInBasket)

    @Delete
    fun deleteProduct(product: ProductsInBasket)

    @Query("SELECT * FROM basket_product_table ORDER BY idProduct DESC")
    fun getAllTasks(): LiveData<List<ProductsInBasket>>
}