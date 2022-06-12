package com.example.klambyshop.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdsResult(
    var ads:String? = null,
):Parcelable
