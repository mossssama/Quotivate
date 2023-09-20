package com.newOs.quotivate.quotes.data.di

import com.newOs.quotivate.QuotesApplication
import com.newOs.quotivate.quotes.data.local.QuoteDao
import com.newOs.quotivate.quotes.data.local.QuoteDatabase
import com.newOs.quotivate.quotes.data.remote.QuotesApiService
import com.newOs.quotivate.quotes.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class QuotesDataModule {

    @Singleton
    @Provides
    fun provideRoomDao(): QuoteDao = QuoteDatabase.getInstance(QuotesApplication.getApplicationContext())


    @Singleton
    @Provides
    fun provideApiService(): QuotesApiService = RetrofitClient.api


}