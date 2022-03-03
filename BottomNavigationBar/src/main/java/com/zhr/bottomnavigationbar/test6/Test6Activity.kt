package com.zhr.bottomnavigationbar.test6

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.SparseIntArray
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.zhr.bottomnavigationbar.R
import com.zhr.bottomnavigationbar.test1.HelpFragment
import com.zhr.bottomnavigationbar.test1.HomeFragment
import com.zhr.bottomnavigationbar.test1.MineFragment
import com.zhr.bottomnavigationbar.test1.SettingFragment
import com.zhr.bottomnavigationbar.test2.TestAdapter
import kotlinx.android.synthetic.main.activity_test6.*
import q.rorbin.badgeview.Badge
import q.rorbin.badgeview.QBadgeView

// BottomNavigationViewEx + ViewPager
// 使用BottomNavigationViewEx的步骤：1、添加一个项目依赖  maven { url "https://jitpack.io" }
// 2、添加两个module依赖  com.github.ittianyu:BottomNavigationViewEx:2.0.4   q.rorbin:badgeview:1.1.3
// 3、gradle.properties下添加两个配置  android.useAndroidX=true   android.enableJetifier=true
class Test6Activity : AppCompatActivity() {

    // 设置第一个按钮为大按钮
    private val bigPosition: Int = 0
    private var fragments: MutableList<Fragment>? = null
    private var sparseIntArray: SparseIntArray? = null
    private var beforePos: Int = -1
    private var badgeView: Badge? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test6)
        initView()
        initData()
        initListener()
    }

    private fun initView() {
        disableAllAnimation()
        setViewVisibility()
        setViewSize()
        setViewColor()
        initCustomTypeface()
        // 添加小红点（小红点添加在底边栏的位置，小红点显示的数字内容）
        badgeView = addBadgeAt(2, 1)
    }

    // https://github.com/ittianyu/BottomNavigationViewEx/blob/master/README_ZH.md
    private fun disableAllAnimation() {
        // 底边栏图标的放大动画
        bottomNavigationViewEx.enableAnimation(true)
        // shift模式的意思就是 tab夸张的变大和移位以及未点击的没有标题
        bottomNavigationViewEx.enableShiftingMode(false)
        bottomNavigationViewEx.enableItemShiftingMode(false)
    }

    private fun setViewVisibility() {
        // 设置文字和图标是否显示 默认为true 显示
        bottomNavigationViewEx.setTextVisibility(true)
        bottomNavigationViewEx.setIconVisibility(true)
    }

    private fun setViewSize() {
        // 设置文字和图标大小
        bottomNavigationViewEx.setTextSize(15f)
        val iconSize = 20f
        bottomNavigationViewEx.setIconSize(iconSize, iconSize)
        bottomNavigationViewEx.setIconSizeAt(bigPosition, 30f, 30f)
        // 设置 icon 的 MarginTop，用于调节图标垂直位置
        bottomNavigationViewEx.setIconMarginTop(bigPosition, BottomNavigationViewEx.dp2px(this, 4f))
        // 设置子项高度
        bottomNavigationViewEx.itemHeight = BottomNavigationViewEx.dp2px(this, iconSize + 80)
    }

    @SuppressLint("ResourceType", "UseCompatLoadingForColorStateLists")
    private fun setViewColor() {
        // 根据item的位置 自定义图标和文字的点击颜色效果
        bottomNavigationViewEx.setIconTintList(bigPosition,
            // 当要获取selector-color的时候，就需要使用getColorStateList()方法；要获取color的时候，就需要使用getColor()方法
            resources.getColorStateList(R.drawable.navigation_selector1))
        bottomNavigationViewEx.setTextTintList(bigPosition,
            resources.getColorStateList(R.drawable.navigation_selector1))
    }

    private fun initCustomTypeface() {
        bottomNavigationViewEx.setTypeface(Typeface.DEFAULT_BOLD)
    }

    private fun addBadgeAt(position: Int, number: Int): Badge {
        return QBadgeView(this)
            .setBadgeNumber(number)
            .setGravityOffset(0f, 0f, true)
            .bindTarget(bottomNavigationViewEx.getBottomNavigationItemView(position))
            .setOnDragStateChangedListener { dragState, badge, targetView ->
                run {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                        Toast.makeText(this@Test6Activity, "Badge is removed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }

    private fun initData() {
        fragments = mutableListOf()
        sparseIntArray = SparseIntArray(4)
        fragments!!.add(HomeFragment.getInstance())
        fragments!!.add(HelpFragment.getInstance())
        fragments!!.add(SettingFragment.getInstance())
        fragments!!.add(MineFragment.getInstance())
        val testAdapter = TestAdapter(supportFragmentManager, fragments!!)
        viewpager.adapter = testAdapter
        viewpager.currentItem = 0
        viewpager.offscreenPageLimit = fragments!!.size
        // 设置点击底边栏ViewPager的切换（底边栏和ViewPager绑定），等同于 setOnNavigationItemSelectedListener（默认没有点击切换动画，设置为true会有）
//        bottomNavigationViewEx.setupWithViewPager(viewpager, true)

        sparseIntArray!!.put(R.id.navigation_home, 0)
        sparseIntArray!!.put(R.id.navigation_help, 1)
        sparseIntArray!!.put(R.id.navigation_setting, 2)
        sparseIntArray!!.put(R.id.navigation_mine, 3)
    }

    private fun initListener() {
        // 设置点击底边栏ViewPager的切换（底边栏和ViewPager绑定），等同于 setupWithViewPager (有点击切换动画)
        bottomNavigationViewEx.setOnNavigationItemSelectedListener {
            // 根据itemId获取到position
            val position = sparseIntArray?.get(it.itemId)
            if (beforePos != position) {
                viewpager.currentItem = position!!
                if (position == 2) {
                    badgeView?.badgeNumber = 0
                }
                beforePos = position
            }
            return@setOnNavigationItemSelectedListener true
        }
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                // ViewPager滑动的时候只操作条目就好了，把滑动的页面位置设置到条目位置，同步起来
                bottomNavigationViewEx.currentItem = position
                if (position == 2) {
                    badgeView?.badgeNumber = 0
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}