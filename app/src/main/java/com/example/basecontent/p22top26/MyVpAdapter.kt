package com.example.basecontent.p22top26

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class MyVpAdapter : PagerAdapter() {

    private lateinit var viewlist: MutableList<View>

    override fun getCount(): Int {
        return viewlist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    // 实现的PageAdapter中少两个方法需要实现，要自己把它添加进去 instantiateItem 和 destroyItem
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewlist[position])
        return viewlist[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewlist[position])
    }

    fun setData(viewlist: MutableList<View>) {
        this.viewlist = viewlist
        notifyDataSetChanged()
    }
}