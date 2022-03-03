package com.zhr.bottomnavigationbar.test5

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.zhr.bottomnavigationbar.R
import com.zhr.bottomnavigationbar.test1.HelpFragment
import com.zhr.bottomnavigationbar.test1.HomeFragment
import com.zhr.bottomnavigationbar.test1.MineFragment
import com.zhr.bottomnavigationbar.test1.SettingFragment
import kotlinx.android.synthetic.main.activity_test5.*
import kotlin.random.Random

// BottomNavigationBar + Fragment
class Test5Activity : AppCompatActivity(), BottomNavigationBar.OnTabSelectedListener {

    private var homeFragment: Fragment? = null
    private var helpFragment: Fragment? = null
    private var settingFragment: Fragment? = null
    private var mineFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test5)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.test5_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 设置条目隐藏
            R.id.barHide -> {
                if (bottomNavigationBar.isShown) {
                    bottomNavigationBar.hide()
                }
            }
            // 设置条目显示
            R.id.barShow -> {
                if (bottomNavigationBar.isHidden) {
                    bottomNavigationBar.show()
                }
            }
            // 设置条目阴影高度
            R.id.barElevation -> bottomNavigationBar.elevation = (Random.nextInt(100)).toFloat()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        // tab的颜色为什么点击是白色，未点是灰色，完全不是图片本来颜色，因为代码中通过tint进行了颜色覆盖
        // 这里添加的是图形、文字标题加上点击颜色和未点击颜色的上色 还可以添加小圆点（TextBadge、ShapeBadge）
        val textBadgeItem = TextBadgeItem()
        // 默认的背景就是椭圆形
        textBadgeItem.setText("10")
            .setTextColor("#FFFFFF")
            .setBackgroundColor("#000000")
            // 设置 隐藏和展示的动画速度 跟setHideOnSelect=true搭配使用
            .setAnimationDuration(500)
            // 设置选中时是否隐藏 默认为false不隐藏
            .setHideOnSelect(true)
        val shapeBadgeItem = ShapeBadgeItem()
            .setShape(ShapeBadgeItem.SHAPE_OVAL)
            .setShapeColor(Color.BLACK)
            .setSizeInDp(this, 10, 10)
            // 设置 隐藏和展示的动画速度 跟setHideOnSelect=true搭配使用
            .setAnimationDuration(500)
            // 设置选中时是否隐藏 默认为false不隐藏
            .setHideOnSelect(true)
        // fixed_bottom_navigation_item 的background设置了水波纹效果，如果要去掉则要把这个背景换掉
        bottomNavigationBar.addItem(BottomNavigationItem(R.mipmap.home_icon, "首页").setInActiveColor(
            "#FF0000").setBadgeItem(textBadgeItem)) //红
            .addItem(BottomNavigationItem(R.mipmap.help_icon,
                "帮助").setInActiveColor("#FFFF00").setBadgeItem(shapeBadgeItem)) // 黄
            .addItem(BottomNavigationItem(R.mipmap.setting_icon,
                "设置").setInActiveColor("#00FF00")) // 绿
            .addItem(BottomNavigationItem(R.mipmap.mine_icon,
                "我的").setInActiveColor("#0000FF")) // 蓝
            // bottomNavigationBar有五种模式
            // default模式：用得较少，tab夸张的变大和移位
            // fixed模式：用得较多，tab稍微变大和变色
            // fixed-no-title模式：用得较少，没有标题
            // shifting模式：用得较少，tab夸张的变大和移位以及未点击的没有标题
            // shifting-no-title模式：用的较少，tab夸张的变大和移位以及没有标题
            .setMode(BottomNavigationBar.MODE_FIXED)
            // default样式：如果模式为fixed是static样式，如果模式为shift是ripple样式
            // static样式：条目白色背景 tab有水波纹效果
            // ripple样式：条目蓝色背景 tab有水波纹效果
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
            // 设置tab被选中的背景颜色(ripple) / 设置条目背景颜色(static)（红）  默认是白色(FFFFFFFF) (ripple样式下，这个和下面的属性弄反了)
            .setBarBackgroundColor("#FF0000")
            // 设置条目背景颜色(ripple)  / 设置tab被选中的背景颜色(static)（黑）  默认是浅绿色
            .setActiveColor("#000000")
            // 设置tab 未被选中的背景颜色（绿）  默认是灰色(FFCCCCCC)
            .setInActiveColor("#00FF00")
            // 设置默认选中的tab，默认是0
            .setFirstSelectedPosition(0)
            // 设置tab被选中的监听
            .setTabSelectedListener(this)
            // 初始化
            .initialise()
    }

    override fun onTabSelected(position: Int) {
        // Tab从未选中到选中
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (position) {
            0 -> {
                // 当未创建对象时才进行创建对象操作  或者将对象设置为单例模式 只允许创建一个对象
                if (null == homeFragment) {
                    homeFragment = HomeFragment.getInstance()
                }
                fragmentTransaction.replace(R.id.framelayout, homeFragment!!)
            }
            1 -> {
                if (null == helpFragment) {
                    helpFragment = HelpFragment.getInstance()
                }
                fragmentTransaction.replace(R.id.framelayout, helpFragment!!)
            }
            2 -> {
                if (null == settingFragment) {
                    settingFragment = SettingFragment.getInstance()
                }
                fragmentTransaction.replace(R.id.framelayout, settingFragment!!)
            }
            3 -> {
                if (null == mineFragment) {
                    mineFragment = MineFragment.getInstance()
                }
                fragmentTransaction.replace(R.id.framelayout, mineFragment!!)
            }
            else -> {
            }
        }
        fragmentTransaction.commit()
    }

    override fun onTabUnselected(position: Int) {
        // Tab从选中到未选中
    }

    override fun onTabReselected(position: Int) {
        // Tab重新被选中
    }
}