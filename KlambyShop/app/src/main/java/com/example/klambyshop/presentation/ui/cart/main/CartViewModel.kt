package com.example.klambyshop.presentation.ui.cart.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klambyshop.data.db.entities.CartEntity
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.repositories.CartRepository
import com.example.klambyshop.data.repositories.FavoriteRepository


class CartViewModel(application: Application): ViewModel() {
    private val mCartRepository : CartRepository = CartRepository(application)
    private val _dataUsers = MutableLiveData<List<CartEntity>>()
    val dataKlamby: LiveData<List<CartEntity>> = _dataUsers

    fun getAllCart(): LiveData<List<CartEntity>> = mCartRepository.getAllCart()

    fun getKlambyById(id: String): LiveData<List<CartEntity>> = mCartRepository.getKlambybyId(id)





}