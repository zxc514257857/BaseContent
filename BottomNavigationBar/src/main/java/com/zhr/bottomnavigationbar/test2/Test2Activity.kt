package com.zhr.bottomnavigationbar.test2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zhr.bottomnavigationbar.R
import com.zhr.bottomnavigationbar.test1.HelpFragment
import com.zhr.bottomnavigationbar.test1.HomeFragment
import com.zhr.bottomnavigationbar.test1.MineFragment
import com.zhr.bottomnavigationbar.test1.SettingFragment
import kotlinx.android.synthetic.main.activity_test2.*

// ConstraintLayout(ImageButton + TextView) + NoScrollViewPager(使用了ViewPager但禁用了ViewPager的滑动事件，可以直接用Fragment替代)
// 点击和滑动无法共用
class Test2Activity : AppCompatActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {

    private var beforeId: Int = 0
    private val TAG: String = "Test2Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        initBotton()
        initData()
    }

    private fun initBotton() {
        group1.setOnClickListener(this)
        group2.setOnClickListener(this)
        group3.setOnClickListener(this)
        group4.setOnClickListener(this)
    }

    private fun initData() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(HomeFragment.getInstance())
        fragments.add(HelpFragment.getInstance())
        fragments.add(SettingFragment.getInstance())
        fragments.add(MineFragment.getInstance())
        val testAdapter = TestAdapter(supportFragmentManager, fragments)
        viewpager.adapter = testAdapter
        val noScroll: Boolean = true
        viewpager.setNoScroll(noScroll)
        if (!noScroll) {
            viewpager.setOnPageChangeListener(this)
            // 打开滚动之后 无法点击切换 这里有点问题 暂时不知道怎么处理  todo
        }
        // 要么就全部缓存，要么就一个都不缓存，每次都缓存前后一个没什么意思
        viewpager.offscreenPageLimit = fragments.size
        viewpager.currentItem = 0
        group1.performClick()
    }

    override fun onClick(view: View?) {
        val id = view?.id
        when (id) {
            R.id.group1 -> showPage(id, 0, true)
            R.id.group2 -> showPage(id, 1, true)
            R.id.group3 -> showPage(id, 2, true)
            R.id.group4 -> showPage(id, 3, true)
            else -> {
            }
        }
    }

    // 这里的点击这一个取消显示上一个 注意理解下
    private fun showPage(id: Int, pos: Int, isWithVp: Boolean) {
        Log.e(TAG, "showPage1: $pos")
        if (beforeId != id) {
            Log.e(TAG, "showPage2: $pos")
            Log.e(TAG, "beforeId: $beforeId,,,id: $id")
            showTab(id)
            if (isWithVp) {
                viewpager.currentItem = pos
            }
            defaultTab(beforeId)
            beforeId = id
        }
    }

    private fun showTab(id: Int) {
        // 底边栏最好通过状态选择器来实现，不要通过Java代码来实现
        var imageButton: ImageButton? = null
        var mipmapRes: Int = 0
        var textView: TextView? = null
        if (id == R.id.group1) {
            textView = tv1
            imageButton = ib1
        } else if (id == R.id.group2) {
            textView = tv2
            imageButton = ib2
        } else if (id == R.id.group3) {
            textView = tv3
            imageButton = ib3
        } else if (id == R.id.group4) {
            textView = tv4
            imageButton = ib4
        }
        mipmapRes = R.mipmap.ic_launcher_round
        imageButton?.setImageResource(mipmapRes)
        textView?.setTextColor(resources.getColor(R.color.teal_700))
    }

    private fun defaultTab(id: Int) {
        var imageButton: ImageButton? = null
        var mipmapRes: Int = 0
        var textView: TextView? = null
        if (id == R.id.group1) {
            textView = tv1
            imageButton = ib1
            mipmapRes = R.mipmap.pic1
        } else if (id == R.id.group2) {
            textView = tv2
            imageButton = ib2
            mipmapRes = R.mipmap.pic2
        } else if (id == R.id.group3) {
            textView = tv3
            imageButton = ib3
            mipmapRes = R.mipmap.pic3
        } else if (id == R.id.group4) {
            textView = tv4
            imageButton = ib4
            mipmapRes = R.mipmap.pic4
        }
        imageButton?.setImageResource(mipmapRes)
        textView?.setTextColor(resources.getColor(R.color.teal_200))
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    // 只用来处理Rv滚动时 tab状态的切换
    override fun onPageSelected(position: Int) {
        if (0 == position) {
            showPage(R.id.group1, position, false)
        } else if (1 == position) {
            showPage(R.id.group2, position, false)
        } else if (2 == position) {
            showPage(R.id.group3, position, false)
        } else if (3 == position) {
            showPage(R.id.group4, position, false)
        } else {
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}
}