package com.newOs.quotivate.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract val quoteDao: QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase = INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, QuoteDatabase::class.java, "quote_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
    }

}