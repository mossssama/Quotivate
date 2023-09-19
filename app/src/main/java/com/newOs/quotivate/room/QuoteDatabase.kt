package com.newOs.quotivate.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Quote::class],
    version = 2,
    exportSchema = false
)
abstract class QuoteDatabase: RoomDatabase() {
    abstract val dao: QuoteDao

    companion object {
        @Volatile
        private var daoInstance: QuoteDao? = null

        private fun buildDatabase(context: Context):QuoteDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                "quotes_database"
            ).fallbackToDestructiveMigration().build()

        fun getInstance(context: Context):QuoteDao{
            synchronized(this){
                if(daoInstance==null){
                    daoInstance= buildDatabase(context).dao
                }
                return daoInstance as QuoteDao
            }
        }

    }

}