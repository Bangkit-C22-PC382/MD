package com.example.klambyshop.data.repositories

import android.app.Application
import com.example.klambyshop.data.db.FavoriteRoomDatabase
import com.example.klambyshop.data.db.dao.KlambyDao
import com.example.klambyshop.data.db.entities.KlambyEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mNotesDao: KlambyDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mNotesDao = db.favoriteDao()
    }

    fun getAllFavorite() = mNotesDao.getAllFavorite()

    fun getUserbyId(id:String) = mNotesDao.getKlambyById(id)

    fun insert(user: KlambyEntity) {
        executorService.execute { mNotesDao.insert(user) }
    }
    fun delete(user: KlambyEntity) {
        executorService.execute { mNotesDao.delete(user) }
    }
    fun update(user: KlambyEntity) {
        executorService.execute { mNotesDao.update(user) }
    }
}