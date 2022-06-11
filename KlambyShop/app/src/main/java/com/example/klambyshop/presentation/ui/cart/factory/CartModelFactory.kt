package com.example.klambyshop.presentation.ui.cart.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.klambyshop.presentation.ui.cart.insert.CartAddUpdateViewModel
import com.example.klambyshop.presentation.ui.cart.main.CartViewModel

class CartModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(CartAddUpdateViewModel::class.java)) {
            return CartAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: CartModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): CartModelFactory {
            if (INSTANCE == null) {
                synchronized(CartModelFactory::class.java) {
                    INSTANCE = CartModelFactory(application)
                }
            }
            return INSTANCE as CartModelFactory
        }
    }
}