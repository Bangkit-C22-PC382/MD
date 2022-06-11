package com.example.klambyshop.presentation.ui.cart.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.klambyshop.data.db.entities.CartEntity
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.repositories.CartRepository
import com.example.klambyshop.data.repositories.FavoriteRepository


class CartAddUpdateViewModel (application: Application): ViewModel(){
    private val mCartRepository: CartRepository = CartRepository(application)

    fun insert(data : CartEntity){
        mCartRepository.insert(data)
    }

    fun delete(data: CartEntity){
        mCartRepository.delete(data)
    }

    fun update(data: CartEntity){
        mCartRepository.update(data)
    }

}