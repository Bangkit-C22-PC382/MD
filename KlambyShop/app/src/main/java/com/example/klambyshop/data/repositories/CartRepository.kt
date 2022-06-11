package com.example.klambyshop.data.repositories

import android.app.Application
import com.example.klambyshop.data.db.CartRoomDatabase
import com.example.klambyshop.data.db.dao.CartDao
import com.example.klambyshop.data.db.entities.CartEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CartRepository(application: Application) {
    private val mNotesDao: CartDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = CartRoomDatabase.getDatabase(application)
        mNotesDao = db.cartDao()
    }

    fun getAllCart() = mNotesDao.getAllFavorite()

    fun getKlambybyId(id:String) = mNotesDao.getKlambyById(id)

    fun insert(data: CartEntity) {
        executorService.execute { mNotesDao.insert(data) }
    }
    fun delete(data: CartEntity) {
        executorService.execute { mNotesDao.delete(data) }
    }
    fun update(data: CartEntity) {
        executorService.execute { mNotesDao.update(data) }
    }
}