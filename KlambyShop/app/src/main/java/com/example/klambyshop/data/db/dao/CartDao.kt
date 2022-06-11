package com.example.klambyshop.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.klambyshop.data.db.entities.CartEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: CartEntity)

    @Update
    fun update(note: CartEntity)

    @Delete
    fun delete(note: CartEntity)

    @Query("SELECT * from klamby_cart ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<CartEntity>>

    @Query("SELECT * from klamby_cart WHERE id = :id")
    fun getKlambyById(id:String): LiveData<List<CartEntity>>

}