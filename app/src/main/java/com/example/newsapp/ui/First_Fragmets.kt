package com.example.newsapp.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.newsapp.R
import com.example.newsapp.ui.viewpager.FirstFragment.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@Suppress("UNREACHABLE_CODE")
class First_Fragmets : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_first__fragmets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ViewPagerAdapter(requireActivity())
        val viewPager2: ViewPager2 = view.findViewById(R.id.viewpager3)
        val tabLayout: TabLayout = view.findViewById(R.id.tablayout)
        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {

                0 -> {
                    tab.text = "Politic"
                }
                1 -> {
                    tab.text = "Business"
                }
                2 -> {
                    tab.text = "Science"
                }
            }
        }.attach()
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setSelectedTabIndicatorHeight(((5 * getResources().getDisplayMetrics().density).toInt()));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#FF000000"));

    }

}