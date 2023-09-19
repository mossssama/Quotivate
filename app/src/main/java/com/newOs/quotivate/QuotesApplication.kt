package com.newOs.quotivate

import android.app.Application
import android.content.Context

class QuotesApplication: Application() {

    init {
        application=this
    }

    companion object{
        private lateinit var application: QuotesApplication
        fun getApplicationContext(): Context = application.applicationContext
    }

}