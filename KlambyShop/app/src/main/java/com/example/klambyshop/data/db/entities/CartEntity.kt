package com.example.klambyshop.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.klambyshop.data.util.CART_TABLE_NAME
import com.example.klambyshop.data.util.KLAMBY_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = CART_TABLE_NAME)
@Parcelize
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
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
): Parcelable

