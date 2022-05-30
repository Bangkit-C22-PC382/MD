package com.example.klambyshop.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.klambyshop.data.db.dao.KlambyDao
import com.example.klambyshop.data.db.entities.KlambyEntity

@Database(entities = [KlambyEntity::class], version = 1)
abstract class FavoriteRoomDatabase: RoomDatabase() {
    abstract  fun favoriteDao(): KlambyDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FavoriteRoomDatabase::class.java, "favorite_database")
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }

}