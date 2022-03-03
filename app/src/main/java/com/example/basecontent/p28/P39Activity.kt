package com.example.basecontent.p28

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.basecontent.R
import com.example.basecontent.databinding.ActivityP39Binding

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p28
 * @Author: zhr
 * @Date: 2022/2/28 21:57
 * @Version: V1.0
 */
class P39Activity : AppCompatActivity() {

    private var binding: ActivityP39Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_p39)
        initViewPager()
    }

    private fun initViewPager() {
        val stringList = mutableListOf<String>()
        stringList.add("哈哈哈1")
        stringList.add("哈哈哈2")
        stringList.add("哈哈哈3")
        stringList.add("哈哈哈4")
        stringList.add("哈哈哈5")
        stringList.add("哈哈哈6")
        val colorList = mutableListOf<Int>()
        colorList.add(Color.BLACK)
        colorList.add(Color.WHITE)
        colorList.add(Color.BLUE)
        colorList.add(Color.RED)
        colorList.add(Color.GRAY)
        colorList.add(Color.GREEN)
        val viewPagerAdapter = ViewPagerAdapter(stringList, colorList)
        binding?.viewpager?.adapter = viewPagerAdapter
        binding?.viewpager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}