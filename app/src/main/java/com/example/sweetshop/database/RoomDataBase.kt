package com.example.sweetshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [ProductsInBasket::class],version = 2,exportSchema = false)
abstract  class RoomDataBase : RoomDatabase() {

    abstract  fun roomDao(): RoomDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDataBase? = null
        @InternalCoroutinesApi
        fun getDatabase(context: Context): RoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "basket_product_table"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }



}