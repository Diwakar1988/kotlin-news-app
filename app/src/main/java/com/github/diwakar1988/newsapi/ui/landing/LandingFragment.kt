package com.github.diwakar1988.newsapi.ui.landing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.utils.Constants
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_landing.*


class LandingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (title in Constants.TAB_TITLE_LIST){
            tabLayout!!.addTab(tabLayout!!.newTab().setText(title))
        }

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = fragmentManager?.let {
            NewsPagerAdapter(
                it
            )
        }
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

    companion object {
        @JvmStatic val TAG = "LandingFragment"
        fun newInstance() = LandingFragment()
    }
}
