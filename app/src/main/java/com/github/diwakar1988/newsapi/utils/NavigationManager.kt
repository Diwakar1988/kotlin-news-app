package com.github.diwakar1988.newsapi.utils

import androidx.fragment.app.Fragment
import com.github.diwakar1988.newsapi.MainActivity
import com.github.diwakar1988.newsapi.R

object NavigationManager {
    private lateinit var activity:MainActivity
    fun setActivity(activity:MainActivity){
        this.activity = activity
    }
    fun add(fragment: Fragment, tag:String?, addToBackStack:Boolean=true){
        if (activity==null){
            return
        }
        val ft = activity
            .supportFragmentManager
            .beginTransaction()
            ft.add(R.id.body, fragment, tag)
            if(addToBackStack){
                ft.addToBackStack(tag)
            }
            ft.commit()
    }
    fun replace(fragment: Fragment, tag:String?, addToBackStack:Boolean=true){
        if (activity==null){
            return
        }
        val ft = activity
            .supportFragmentManager
            .beginTransaction()
        ft.replace(R.id.body, fragment, tag)
        if(addToBackStack){
            ft.addToBackStack(tag)
        }
        ft.commit()
    }
    fun pop () = activity.supportFragmentManager.popBackStack()
}