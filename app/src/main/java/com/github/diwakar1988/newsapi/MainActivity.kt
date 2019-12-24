package com.github.diwakar1988.newsapi

import android.os.Bundle
import com.github.diwakar1988.newsapi.core.BaseActivity
import com.github.diwakar1988.newsapi.ui.landing.LandingFragment
import com.github.diwakar1988.newsapi.utils.NavigationManager

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationManager.setActivity(this)
        setContentView(R.layout.activity_main)
        NavigationManager.add(LandingFragment.newInstance(),LandingFragment.TAG,false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true;
    }
}
