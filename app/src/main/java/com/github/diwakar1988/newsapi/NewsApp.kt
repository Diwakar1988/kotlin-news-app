package com.github.diwakar1988.newsapi

import android.app.Application

class NewsApp: Application() {

    companion object {
        const val CONSTANT = 12
        lateinit var instance:NewsApp
    }

    override fun onCreate() {
        instance= this
        super.onCreate()
    }

}