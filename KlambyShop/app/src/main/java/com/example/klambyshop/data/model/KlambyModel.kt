package com.example.klambyshop.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KlambyModel(
    val id:String,
    val status: Boolean,
    val title: String,
    val image_url: String,
    val description: String,
    val create_at: String,
    val price:String,
    val color: String,
    val size:String,
    val place:String,
    val seller:String,
    val seller_profile:String
):Parcelable
