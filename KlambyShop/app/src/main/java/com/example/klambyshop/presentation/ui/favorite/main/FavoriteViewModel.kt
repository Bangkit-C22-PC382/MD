package com.example.klambyshop.presentation.ui.favorite.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.repositories.FavoriteRepository


class FavoriteViewModel(application: Application): ViewModel() {
    private val mFavoriteRepository :FavoriteRepository = FavoriteRepository(application)
    private val _dataUsers = MutableLiveData<List<KlambyEntity>>()
    val dataKlamby: LiveData<List<KlambyEntity>> = _dataUsers

    fun getAllFavorite(): LiveData<List<KlambyEntity>> = mFavoriteRepository.getAllFavorite()

    fun getKlambyById(id: String): LiveData<List<KlambyEntity>> = mFavoriteRepository.getUserbyId(id)





}