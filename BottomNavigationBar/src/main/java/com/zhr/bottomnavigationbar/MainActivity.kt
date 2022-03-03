package com.zhr.bottomnavigationbar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.zhr.bottomnavigationbar.test1.Test1Activity
import com.zhr.bottomnavigationbar.test2.Test2Activity
import com.zhr.bottomnavigationbar.test3.Test3Activity
import com.zhr.bottomnavigationbar.test4.Test4Activity
import com.zhr.bottomnavigationbar.test5.Test5Activity
import com.zhr.bottomnavigationbar.test6.Test6Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1.setOnClickListener(this)
        tv2.setOnClickListener(this)
        tv3.setOnClickListener(this)
        tv4.setOnClickListener(this)
        tv5.setOnClickListener(this)
        tv6.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            // BottomNavigationView + Fragment
            R.id.tv1 -> startActivity(Intent(this, Test1Activity::class.java))
            // ConstraintLayout + NoScrollViewPager
            R.id.tv2 -> startActivity(Intent(this, Test2Activity::class.java))
            // RadioGroup + ViewPager
            R.id.tv3 -> startActivity(Intent(this, Test3Activity::class.java))
            // RadioGroup + ViewPager2 + BigCenter
            R.id.tv4 -> startActivity(Intent(this, Test4Activity::class.java))
            // BottomNavigationBar + Fragment
            R.id.tv5 -> startActivity(Intent(this, Test5Activity::class.java))
            // BottomNavigationViewEx + Fragment
            R.id.tv6 -> startActivity(Intent(this, Test6Activity::class.java))
            else -> {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 设置沉浸式状态栏（状态栏颜色和布局的整体颜色相同）
            R.id.a1 -> BarUtils.transparentStatusBar(this)
            // 设置状态栏可见
            R.id.a2 -> BarUtils.setStatusBarVisibility(this, true)
            // 设置状态栏不可见
            R.id.a3 -> BarUtils.setStatusBarVisibility(this, false)
            // 设置状态栏文字异色
            R.id.a4 -> BarUtils.setStatusBarLightMode(this, true)
            // 设置状态栏文字不异色
            R.id.a5 -> BarUtils.setStatusBarLightMode(this, false)
            // 设置状态栏背景红色
            R.id.a6 -> BarUtils.setStatusBarColor(this, Color.RED)
            // 设置通知栏可见
            R.id.a7 -> BarUtils.setNotificationBarVisibility(true)
            // 设置导航栏可见
            R.id.a8 -> BarUtils.setNavBarVisibility(this, true)
            // 设置导航栏不可见
            R.id.a9 -> BarUtils.setNavBarVisibility(this, false)
            // 设置导航栏背景红色
            R.id.a10 -> BarUtils.setNavBarColor(this, Color.RED)
            // 设置导航栏浅色模式(开)  目前测试效果不大
            R.id.a11 -> BarUtils.setNavBarLightMode(this, true)
            // 设置导航栏浅色模式(关)  目前测试效果不大
            R.id.a12 -> BarUtils.setNavBarLightMode(this, false)
        }
        return super.onOptionsItemSelected(item)
    }
}