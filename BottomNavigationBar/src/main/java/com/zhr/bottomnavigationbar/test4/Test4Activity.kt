package com.zhr.bottomnavigationbar.test4

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.zhr.bottomnavigationbar.R
import com.zhr.bottomnavigationbar.test1.HelpFragment
import com.zhr.bottomnavigationbar.test1.HomeFragment
import com.zhr.bottomnavigationbar.test1.MineFragment
import com.zhr.bottomnavigationbar.test1.SettingFragment
import kotlinx.android.synthetic.main.activity_test3.radioGroup
import kotlinx.android.synthetic.main.activity_test4.*

// RadioGroup + ViewPager2 + BigCenter
class Test4Activity : AppCompatActivity() {

    private var beforeId: Int = R.id.rb_home
    private var beforePos: Int = 0
    private var vp2PageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test4)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewpager.unregisterOnPageChangeCallback(vp2PageChangeCallback as ViewPager2.OnPageChangeCallback)
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(HomeFragment.getInstance())
        fragments.add(HelpFragment.getInstance())
        fragments.add(CenterFragment.getInstance())
        fragments.add(SettingFragment.getInstance())
        fragments.add(MineFragment.getInstance())
        val testAdapter = TestAdapter(supportFragmentManager, lifecycle, fragments)
        viewpager.adapter = testAdapter
        // ViewPager2如何修改滑动速度 没有找到方法 todo
        viewpager.currentItem = 0
        // 保持的条目数量好像没有生效 todo
        viewpager.offscreenPageLimit = 1
//        viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vp2PageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    iv.setImageResource(R.drawable.radiobutton_center_background2)
                } else {
                    val radioButton = radioGroup.getChildAt(position) as RadioButton
                    radioButton.isChecked = true
                    if (beforePos == 2) {
                        iv.setImageResource(R.drawable.radiobutton_center_background1)
                    }
                }
                beforePos = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        }
        viewpager.registerOnPageChangeCallback(vp2PageChangeCallback as ViewPager2.OnPageChangeCallback)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (checkedId) {
                    R.id.rb_home -> {
                        // 这里同样的内容怎么when 穿透，同样的内容这样写太累赘了 todo
                        if (checkedId != beforeId) {
                            viewpager.currentItem = 0
                            if (beforeId == R.id.iv) {
                                iv.setImageResource(R.drawable.radiobutton_center_background1)
                            }
                            beforeId = checkedId
                        }
                    }
                    R.id.rb_help -> {
                        if (checkedId != beforeId) {
                            viewpager.currentItem = 1
                            if (beforeId == R.id.iv) {
                                iv.setImageResource(R.drawable.radiobutton_center_background1)
                            }
                            beforeId = checkedId
                        }
                    }
                    R.id.rb_center -> {
                        centerCheck()
                    }
                    R.id.rb_setting -> {
                        if (checkedId != beforeId) {
                            viewpager.currentItem = 3
                            if (beforeId == R.id.iv) {
                                iv.setImageResource(R.drawable.radiobutton_center_background1)
                            }
                            beforeId = checkedId
                        }
                    }
                    R.id.rb_mine -> {
                        if (checkedId != beforeId) {
                            viewpager.currentItem = 4
                            if (beforeId == R.id.iv) {
                                iv.setImageResource(R.drawable.radiobutton_center_background1)
                            }
                            beforeId = checkedId
                        }
                    }
                    else -> {
                    }
                }
            }
        }
        iv.setOnClickListener {
            centerCheck()
        }
    }

    private fun centerCheck() {
        if (iv.id != beforeId) {
            viewpager.currentItem = 2
            iv.setImageResource(R.drawable.radiobutton_center_background2)
            val radioButton = findViewById<RadioButton>(beforeId)
            radioButton.isChecked = false
            beforeId = iv.id
        }
    }
}