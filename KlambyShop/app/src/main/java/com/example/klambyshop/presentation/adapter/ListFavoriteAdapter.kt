package com.example.klambyshop.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.presentation.adapter.viewholder.ListFavoriteViewHolder
import com.example.klambyshop.presentation.ui.favorite.insert.FavoriteAddUpdateViewModel

class ListFavoriteAdapter(private val listKlamby: List<KlambyModel>, private val AddUpdateViewModel : FavoriteAddUpdateViewModel):
    RecyclerView.Adapter<ListFavoriteViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListFavoriteViewHolder(
            inflater.inflate(R.layout.rv_item_favorite, parent,false)
        )
    }

    override fun onBindViewHolder(holder: ListFavoriteViewHolder, position: Int) {
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