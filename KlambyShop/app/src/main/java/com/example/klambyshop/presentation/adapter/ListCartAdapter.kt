package com.example.klambyshop.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.presentation.adapter.viewholder.ListCartViewHolder
import com.example.klambyshop.presentation.adapter.viewholder.ListFavoriteViewHolder
import com.example.klambyshop.presentation.ui.cart.insert.CartAddUpdateViewModel
import com.example.klambyshop.presentation.ui.favorite.insert.FavoriteAddUpdateViewModel

class ListCartAdapter(private val listKlamby: List<KlambyModel>, private val AddUpdateViewModel : CartAddUpdateViewModel):
    RecyclerView.Adapter<ListCartViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListCartViewHolder(
            inflater.inflate(R.layout.rv_item_cart, parent,false)
        )
    }

    override fun onBindViewHolder(holder: ListCartViewHolder, position: Int) {
        holder.onBlind(listKlamby[position],AddUpdateViewModel)
        holder.itemView.setOnClickListener{
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKlamby[holder.adapterPosition]) }
        }
    }

    override fun getItemCount(): Int {
        return listKlamby.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(klamby: KlambyModel)
    }

}