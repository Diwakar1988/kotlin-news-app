package com.github.diwakar1988.newsapi.landing.utils

import android.os.Handler
import android.os.Looper

class Async{
    companion object{
        private val HANDLER:Handler = Handler(Looper.getMainLooper())
        @JvmStatic
        fun runOnUi(runnable: Runnable){
            HANDLER.post(runnable)
        }
    }
}