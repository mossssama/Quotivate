package com.newOs.quotivate.quotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalQuote::class],
    version = 3,
    exportSchema = false
)
abstract class QuoteDatabase: RoomDatabase() {
    abstract val dao: QuoteDao

    companion object {
        @Volatile
        private var daoInstance: QuoteDao? = null

        private fun buildDatabase(context: Context): QuoteDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                "quotes_database"
            ).fallbackToDestructiveMigration().build()

        fun getInstance(context: Context): QuoteDao {
            synchronized(this){
                if(daoInstance ==null){
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as QuoteDao
            }
        }

    }

}