package com.example.klambyshop.presentation.ui.favorite.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.klambyshop.data.db.entities.KlambyEntity
import com.example.klambyshop.data.repositories.FavoriteRepository


class FavoriteAddUpdateViewModel (application: Application): ViewModel(){
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(user : KlambyEntity){
        mFavoriteRepository.insert(user)
    }

    fun delete(user: KlambyEntity){
        mFavoriteRepository.delete(user)
    }

    fun update(user: KlambyEntity){
        mFavoriteRepository.update(user)
    }

}