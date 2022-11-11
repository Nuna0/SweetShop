package com.example.sweetshop.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderModel(
    @SerializedName("ok")
    val ok:Boolean,
    @SerializedName("result")
    val result: Result
):Parcelable

@Parcelize
data class Result(
    @SerializedName("message_id")
    val message_id:Int,
    @SerializedName("date")
    val date:Int,
    @SerializedName("text")
    val text:String,
):Parcelable