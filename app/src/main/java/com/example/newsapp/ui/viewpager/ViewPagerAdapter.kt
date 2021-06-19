package com.example.newsapp.ui.viewpager.FirstFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.search.SearchFragment
import com.example.newsapp.ui.viewpager.ViewPager.Fragment.BusinessFragment
import com.example.newsapp.ui.viewpager.ViewPager.Fragment.PoliticsFragment
import com.example.newsapp.ui.viewpager.ViewPager.Fragment.ScienceFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PoliticsFragment()
            1 -> return BusinessFragment()
            2 -> return ScienceFragment()
            else -> {
                return SearchFragment()
            }
        }
    }
}