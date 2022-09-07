package com.example.sweetshop.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "basket_product_table")
data class ProductsInBasket(
    @PrimaryKey(autoGenerate = true)
    val idProduct: Int,
    val name: String,
    val image: String,
    val price: String,
    val presence: String?,
    val mass: String,
    val category: String,
): Parcelable