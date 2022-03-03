package com.zhr.bottomnavigationbar.test1

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhr.bottomnavigationbar.R
import kotlinx.android.synthetic.main.activity_test1.*

// BottomNatigationView + Fragment(是否使用ViewPager 是看是否需要滑动切换页面 如果不需要滑动切换页面 使用Fragment切换就好了)
// 注意Fragment的add hide replace操作
class Test1Activity : AppCompatActivity() {

    private var homeFragment: Fragment? = null
    private var helpFragment: Fragment? = null
    private var settingFragment: Fragment? = null
    private var mineFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        initBottomNavigation()
        showHome()
    }

    private fun initBottomNavigation() {
        // 设置item点击切换
        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> showHome()
                R.id.navigation_help -> showHelp()
                R.id.navigation_setting -> showSetting()
                R.id.navigation_mine -> showMine()
                else -> {
                }
            }
            true
        })
        // 设置拦截长按事件 处理长按弹出toast的情况
        // 获取子item的长按事件
        // 经测试没有处理掉 还是会出现Toast的情况
        val bottomChildView = navigation.getChildAt(0)
        bottomChildView.findViewById<ViewGroup>(R.id.navigation_home)
            .setOnLongClickListener { true }
        bottomChildView.findViewById<ViewGroup>(R.id.navigation_help)
            .setOnLongClickListener { true }
        bottomChildView.findViewById<ViewGroup>(R.id.navigation_setting)
            .setOnLongClickListener { true }
        bottomChildView.findViewById<ViewGroup>(R.id.navigation_mine)
            .setOnLongClickListener { true }
    }

    private fun showHome() {
//        addHome()
        replaceHome()
    }

    // 使用add hide只会显示和隐藏 不会刷新页面数据
    private fun addHome() {
        // add - hide - show - commit
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == homeFragment) {
            homeFragment = HomeFragment.getInstance()
            fragmentTransaction.add(R.id.framelayout, homeFragment!!)
        }
        hideAll()
        fragmentTransaction.show(homeFragment!!)
        fragmentTransaction.commit()
    }

    // 使用replace 可能切换稍慢点 但会刷新页面数据
    private fun replaceHome() {
        // replace - commit
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == homeFragment) {
            homeFragment = HomeFragment.getInstance()
        }
        fragmentTransaction.replace(R.id.framelayout, homeFragment!!)
        fragmentTransaction.commit()
    }

    private fun showHelp() {
//        addHelp()
        replaceHelp()
    }

    private fun addHelp() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == helpFragment) {
            helpFragment = HelpFragment.getInstance()
            fragmentTransaction.add(R.id.framelayout, helpFragment!!)
        }
        hideAll()
        fragmentTransaction.show(helpFragment!!)
        fragmentTransaction.commit()
    }

    private fun replaceHelp() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == helpFragment) {
            helpFragment = HelpFragment.getInstance()
        }
        fragmentTransaction.replace(R.id.framelayout, helpFragment!!)
        fragmentTransaction.commit()
    }

    private fun showSetting() {
//        addSetting()
        replaceSetting()
    }

    private fun addSetting() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == settingFragment) {
            settingFragment = SettingFragment.getInstance()
            fragmentTransaction.add(R.id.framelayout, settingFragment!!)
        }
        hideAll()
        fragmentTransaction.show(settingFragment!!)
        fragmentTransaction.commit()
    }

    private fun replaceSetting() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == settingFragment) {
            settingFragment = SettingFragment.getInstance()
        }
        fragmentTransaction.replace(R.id.framelayout, settingFragment!!)
        fragmentTransaction.commit()
    }

    private fun showMine() {
//        addMine()
        replaceMine()
    }

    private fun addMine() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == mineFragment) {
            mineFragment = MineFragment.getInstance()
            fragmentTransaction.add(R.id.framelayout, mineFragment!!)
        }
        hideAll()
        fragmentTransaction.show(mineFragment!!)
        fragmentTransaction.commit()
    }

    private fun replaceMine() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (null == mineFragment) {
            mineFragment = MineFragment.getInstance()
        }
        fragmentTransaction.replace(R.id.framelayout, mineFragment!!)
        fragmentTransaction.commit()
    }

    private fun hideAll() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment!!)
        }
        if (helpFragment != null) {
            fragmentTransaction.hide(helpFragment!!)
        }
        if (settingFragment != null) {
            fragmentTransaction.hide(settingFragment!!)
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment!!)
        }
        fragmentTransaction.commit()
    }
}