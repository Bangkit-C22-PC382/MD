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

    fun getKlambybyId(id:String) = mNotesDao.getKlambyById(id)

    fun insert(data: KlambyEntity) {
        executorService.execute { mNotesDao.insert(data) }
    }
    fun delete(data: KlambyEntity) {
        executorService.execute { mNotesDao.delete(data) }
    }
    fun update(data: KlambyEntity) {
        executorService.execute { mNotesDao.update(data) }
    }
}