package com.github.diwakar1988.newsapi.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.diwakar1988.newsapi.MainActivity
import com.github.diwakar1988.newsapi.R

object NavigationManager:FragmentManager.OnBackStackChangedListener {
    private lateinit var activity:MainActivity
    fun setActivity(activity:MainActivity){
        this.activity = activity
        activity.supportFragmentManager.addOnBackStackChangedListener(this)
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
    override fun onBackStackChanged() {
        if(activity.supportFragmentManager.backStackEntryCount==0){
            activity.supportActionBar?.setHomeButtonEnabled(false)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            activity.supportActionBar?.setTitle(R.string.app_name)
        }else{
            activity.supportActionBar?.setHomeButtonEnabled(true)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.title = null
        }
    }
}