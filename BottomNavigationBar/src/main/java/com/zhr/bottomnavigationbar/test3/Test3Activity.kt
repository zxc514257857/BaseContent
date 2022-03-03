package com.zhr.bottomnavigationbar.test3

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zhr.bottomnavigationbar.R
import com.zhr.bottomnavigationbar.test1.HelpFragment
import com.zhr.bottomnavigationbar.test1.HomeFragment
import com.zhr.bottomnavigationbar.test1.MineFragment
import com.zhr.bottomnavigationbar.test1.SettingFragment
import com.zhr.bottomnavigationbar.test2.*
import kotlinx.android.synthetic.main.activity_test3.*

// RadioGroup + ViewPager 滑动和点击切换都可以正常使用
class Test3Activity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewpager.removeOnPageChangeListener(this)
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(HomeFragment.getInstance())
        fragments.add(HelpFragment.getInstance())
        fragments.add(SettingFragment.getInstance())
        fragments.add(MineFragment.getInstance())
        val testAdapter = TestAdapter(supportFragmentManager, fragments)
        viewpager.adapter = testAdapter
        viewpager.currentItem = 0
        // 要么就全部缓存，要么就一个都不缓存，每次都缓存前后一个没什么意思
        viewpager.offscreenPageLimit = fragments.size
//        viewpager.offscreenPageLimit = 0
        viewpager.addOnPageChangeListener(this)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            run {
                // 方式一：知道id写死就好
//                when (checkedId) {
//                    R.id.rb_home -> viewpager.currentItem = 0
//                    R.id.rb_help -> viewpager.currentItem = 1
//                    R.id.rb_setting -> viewpager.currentItem = 2
//                    R.id.rb_mine -> viewpager.currentItem = 3
//                }
                // 方法二：使用传参的数据
                val childCount = group.childCount
                for (i in 0 until childCount) {
                    if (group.getChildAt(i).id == checkedId) {
                        viewpager.currentItem = i
                        return@run
                    }
                }
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        val childButton = radioGroup.getChildAt(position) as RadioButton
        childButton.isChecked = true
    }

    override fun onPageScrollStateChanged(state: Int) {}
}