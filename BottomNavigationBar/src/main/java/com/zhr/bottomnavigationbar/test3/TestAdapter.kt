package com.zhr.bottomnavigationbar.test3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TestAdapter(
    fm: FragmentManager, private val fragments: List<Fragment>,
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}