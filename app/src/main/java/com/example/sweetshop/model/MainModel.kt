package com.example.sweetshop.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainModel(
    @SerializedName("allCategory")
    val categories: ArrayList<Categories>,
    @SerializedName("items")
    val catalog: ArrayList<Catalog>,
    @SerializedName("headers")
    val header: ArrayList<Header>
) :Parcelable

@Parcelize
data class Categories(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("name")
    val name: String,
):Parcelable

@Parcelize
data class Catalog(
    @SerializedName("nameCategory")
    val nameCategory: String,
    @SerializedName("products")
    val products: ArrayList<Products>,
):Parcelable

@Parcelize
data class Products(
    @SerializedName("id")
    val idProduct: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("img")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("presence")
    val presence: String?,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("category")
    val category: String,
):Parcelable

@Parcelize
data class Header(
    @SerializedName("imgMin")
    val imgMin: String,
    @SerializedName("imgMax")
    val imgMax: ArrayList<String>,
    @SerializedName("id")
    val idHeader: String,
):Parcelable
