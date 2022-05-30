package com.example.klambyshop.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klambyshop.R
import com.example.klambyshop.data.model.KlambyModel
import com.example.klambyshop.presentation.adapter.viewholder.ListKlambyViewHolder

class ListKlambyAdapter(private val listKlamby: MutableList<KlambyModel>):
    RecyclerView.Adapter<ListKlambyViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListKlambyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListKlambyViewHolder(
            inflater.inflate(R.layout.rv_item_klamby, parent,false)
        )

    }

    override fun onBindViewHolder(holder: ListKlambyViewHolder, position: Int) {
        holder.onBlind(listKlamby[position])
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