package com.example.klambyshop.presentation.ui.favorite.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.repositories.FavoriteRepository


class FavoriteAddUpdateViewModel (application: Application): ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(data : KlambyEntity){
        mFavoriteRepository.insert(data)
    }

    fun delete(data: KlambyEntity){
        mFavoriteRepository.delete(data)
    }

    fun update(data: KlambyEntity){
        mFavoriteRepository.update(data)
    }

}