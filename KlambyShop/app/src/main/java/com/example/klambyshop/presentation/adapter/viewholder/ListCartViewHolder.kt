package com.example.klambyshop.presentation.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klambyshop.R
import com.example.klambyshop.data.db.entities.CartEntity
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.presentation.ui.cart.insert.CartAddUpdateViewModel

class ListCartViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val iv_image: ImageView = view.findViewById(R.id.iv_image)
    private val title_baju: TextView = view.findViewById(R.id.name_product)
    private val price_baju: TextView = view.findViewById(R.id.text_price)
    private val place_baju: TextView = view.findViewById(R.id.text_location)
    private val size_baju: TextView = view.findViewById(R.id.tv_size)
    private val username:TextView = view.findViewById(R.id.username)
    private val iv_delate:ImageView = view.findViewById(R.id.iv_delate)
//    private val color_baju: TextView = view.findViewById(R.id.iv_color)

    val context = view.context


    fun onBlind(element: KlambyModel,AddUpdateViewModel : CartAddUpdateViewModel){
        title_baju.text = element.title
        price_baju.text = element.price
        place_baju.text = element.place
        size_baju.text = element.size
        username.text = element.seller
        iv_delate.setOnClickListener {
            val data = CartEntity(
                element.id,
                element.status,
                element.title,
                element.image_url,
                element.description,
                element.create_at,
                element.price,
                element.color,
                element.size,
                element.place,
                element.seller,
                element.seller_profile

            )
            AddUpdateViewModel.delete(data)

        }
//        Glide.with(context)
//            .load(element.image_url)
//            .centerCrop()
//            .into(iv_image)
    }

}