package com.github.diwakar1988.newsapi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.diwakar1988.newsapi.core.BaseActivity
import com.github.diwakar1988.newsapi.ui.landing.LandingFragment
import com.github.diwakar1988.newsapi.ui.landing.NewsPagerAdapter
import com.github.diwakar1988.newsapi.utils.Constants
import com.github.diwakar1988.newsapi.utils.NavigationManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationManager.setActivity(this)
        setContentView(R.layout.activity_main)
        NavigationManager.add(LandingFragment.newInstance(),LandingFragment.TAG,false)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.landing, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true;
    }
}
