package com.example.klambyshop.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.klambyshop.data.db.entities.KlambyEntity

@Dao
interface KlambyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: KlambyEntity)

    @Update
    fun update(note: KlambyEntity)

    @Delete
    fun delete(note: KlambyEntity)

    @Query("SELECT * from klamby_favorite ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<KlambyEntity>>

    @Query("SELECT * from klamby_favorite WHERE id = :id")
    fun getKlambyById(id:String): LiveData<List<KlambyEntity>>

}