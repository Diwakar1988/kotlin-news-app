package com.github.diwakar1988.newsapi.landing

import android.os.Bundle
import android.view.Menu
import androidx.viewpager.widget.ViewPager
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.core.BaseActivity
import com.github.diwakar1988.newsapi.utils.Constants
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)

        for (title in Constants.TAB_TITLE_LIST){
            tabLayout!!.addTab(tabLayout!!.newTab().setText(title))
        }

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(supportFragmentManager)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.landing, menu)
        return true
    }
}
