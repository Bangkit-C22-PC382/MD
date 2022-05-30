package com.example.klambyshop.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel

class ListKlambyViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val iv_image: ImageView = view.findViewById(R.id.iv_image)
    private val title_baju: TextView = view.findViewById(R.id.title_baju)
    private val price_baju: TextView = view.findViewById(R.id.price_baju)
    private val place_baju: TextView = view.findViewById(R.id.place_baju)
    val context = view.context


    fun onBlind(element:KlambyModel){
        title_baju.text = element.title
        price_baju.text = element.price
        place_baju.text = element.place
//        Glide.with(context)
//            .load(element.image_url)
//            .centerCrop()
//            .into(iv_image)
    }

}