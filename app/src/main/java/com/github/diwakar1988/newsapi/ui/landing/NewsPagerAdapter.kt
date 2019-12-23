package com.github.diwakar1988.newsapi.ui.landing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.diwakar1988.newsapi.ui.listing.NewsListFragment
import com.github.diwakar1988.newsapi.utils.Constants

class NewsPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {
    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        return NewsListFragment.newInstance(Constants.TAB_TITLE_LIST[position])
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return Constants.TAB_TITLE_LIST.size
    }

}
