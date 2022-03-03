package com.zhr.recyclerviewtest

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zhr.recyclerviewtest.adapter.*
import com.zhr.recyclerviewtest.bean.Datas
import com.zhr.recyclerviewtest.bean.ItemBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

// https://www.sunofbeach.net/wenda
// https://www.bilibili.com/video/BV1Dt411L74N?p=3

// 学习optionsmenu 菜单栏的写法
// 学习RecyclerView的基础布局用法 LinearLayoutManger  GridLayoutManager  StaggerGridLayoutManager
// 学习子类抽取父类：  父类定义成abstract的，将子类共性方法抽取到父类，异性方法定义成抽象方法让子类去实现
// 学习接口回调的写法：（接口或者是抽象类 都是让子类去实现的）  三行代码三句话：先设置监听 再实现监听 再通过监听传递参数
// 学习条目点击事件
// 学习多条目布局
// 学习下拉刷新
// 学习上滑加载更多
class MainActivity : AppCompatActivity(), RvBaseAdapter.OnItemClickListener {

    private val TAG: String = "MainActivity"
    private var mDatas: MutableList<ItemBean>? = null

    //    private var adapter: RecyclerViewBaseAdapter? = null
    private var adapter: RvBaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        // 默认效果ListView
        showListView(isStandard = true, isReverse = false)
        // 下拉刷新
        swipeToRefresh()
        // 上滑加载更多
        slideUpToRefresh()
    }

    private fun swipeToRefresh() {
        // 设置下拉刷新可用 默认可用  false的话表示不启用下拉刷新
        swipeRefreshLayout.isEnabled = true
        // 设置下拉刷新的箭头颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_500)
        // 设置下拉刷新的圆圈背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.teal_200)
        // 设置触发下拉刷新的距离
        swipeRefreshLayout.setDistanceToTriggerSync(100)
        // 设置下拉刷新监听
        swipeRefreshLayout.setOnRefreshListener {
            val itemBean = ItemBean()
            itemBean.des = "下拉刷新新增数据"
            itemBean.pic = mDatas?.get(Random.nextInt(mDatas?.size!!))?.pic
            // 图片的摆放位置 多条目布局里面需要的字段
            itemBean.type = mDatas?.get(Random.nextInt(3))?.type
            mDatas?.add(0, itemBean)
            Handler().postDelayed({
                swipeRefreshLayout.isRefreshing = false
                adapter?.notifyDataSetChanged()
            }, 2000)
        }
    }

    private fun slideUpToRefresh() {
        // 先添加一个默认条目，把下面的条目顶上去(如果要计算条目的总个数需要将这个默认条目删除掉)
        val itemBean = ItemBean()
        itemBean.des = "我是上滑加载更多默认条目"
        itemBean.pic = mDatas?.get(Random.nextInt(mDatas?.size!!))?.pic
        // 图片的摆放位置 多条目布局里面需要的字段
        itemBean.type = mDatas?.get(Random.nextInt(3))?.type
        mDatas?.add(itemBean)
        // 只能用于ListView样式的布局中
        if (adapter is RvSub1Adapter) {
            (adapter as RvSub1Adapter).setOnSlideUpToRefreshListener(object :
                RvSub1Adapter.OnSlideUpToRefreshListener {
                override fun OnSlideUpToRefresh(rvSub1LoadmoreHolder: RvSub1Adapter.RvSub1LoadmoreHolder) {
                    Handler().postDelayed({
                        // 使用随机数模拟加载成功
                        if (Random.nextInt() % 2 == 0) {
                            val itemBean1 = ItemBean()
                            itemBean1.des = "上滑加载新增数据"
                            itemBean1.pic = mDatas?.get(Random.nextInt(mDatas?.size!!))?.pic
                            // 图片的摆放位置 多条目布局里面需要的字段
                            itemBean1.type = mDatas?.get(Random.nextInt(3))?.type
                            mDatas?.add(mDatas!!.size - 1, itemBean1)
                            rvSub1LoadmoreHolder.updateState(RvSub1Adapter.STATE_NORMAL)
                            adapter?.notifyDataSetChanged()
                        } else {
                            rvSub1LoadmoreHolder.updateState(RvSub1Adapter.STATE_RELOAD)
                        }
                    }, 2000)
                }
            })
        }
    }

    private fun initData() {
        // List<DataBean> ---> Adapter ---> setAdapter ---> 显示数据
        // 创建模拟数据
        mDatas = mutableListOf()
        val pics = Datas().pic()
        for (i in pics.indices) {
            val itemBean = ItemBean()
            itemBean.des = "图片 - $i"
            itemBean.pic = pics[i]
            // 条目类型随机生成 012
            itemBean.type = Random.nextInt(3)
            // 添加数据到数据集
            mDatas?.add(itemBean)
        }
    }

    // 创建右上角的菜单栏
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 当右上角菜单栏被选中的时候调用
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.listView -> {
                // 同一方向，单一item
                Log.i(TAG, "进入listView效果菜单")
            }
            R.id.listView_vertical_standard -> {
                showListView(isStandard = true, isReverse = false)
            }
            R.id.listView_vertical_reverse -> {
                showListView(isStandard = true, isReverse = true)
            }
            R.id.listView_horizontal_standard -> {
                showListView(isStandard = false, isReverse = false)
            }
            R.id.listView_horizontal_reverse -> {
                showListView(isStandard = false, isReverse = true)
            }

            R.id.gridView -> {
                // 同一方向，多个item
                Log.i(TAG, "进入gridView效果菜单")
            }
            R.id.gridView_vertical_standard -> {
                showGridView(isStandard = true, isReverse = false)
            }
            R.id.gridView_vertical_reverse -> {
                showGridView(isStandard = true, isReverse = true)
            }
            R.id.gridView_horizontal_standard -> {
                showGridView(isStandard = false, isReverse = false)
            }
            R.id.gridView_horizontal_reverse -> {
                showGridView(isStandard = false, isReverse = true)
            }

            R.id.staggerView -> {
                // item参差不齐，像瀑布一样的效果
                Log.i(TAG, "进入staggerView效果菜单")
            }
            R.id.staggerView_vertical_standard -> {
                showStaggerView(isStandard = true, isReverse = false)
            }
            R.id.staggerView_vertical_reverse -> {
                showStaggerView(isStandard = true, isReverse = true)
            }
            R.id.staggerView_horizontal_standard -> {
                showStaggerView(isStandard = false, isReverse = false)
            }
            R.id.staggerView_horizontal_reverse -> {
                showStaggerView(isStandard = false, isReverse = true)
            }

            R.id.multiply_item -> {
                Log.i(TAG, "多条目布局")
                // 这里也可以写成垂直 翻转的那些效果的 暂时先不写了
                mDatas?.clear()
                initData()
                showMultiplyItem(isStandard = true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 是否垂直布局  是否反向排列
    private fun showListView(isStandard: Boolean, isReverse: Boolean) {
        recyclerview.layoutManager = LinearLayoutManager(this,
            if (isStandard) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, isReverse)
        // 初始实现，未抽取父类 - version1
//        val listViewAdapter = ListViewAdapter(this, mDatas!!)
        // 抽取了共同父类，大部分是父类实现 - version2
//        adapter = RecyclerViewSub1Adapter(this, mDatas!!)
        // 抽取了共同父类，大部分是子类实现 - version3
        adapter = RvSub1Adapter(this, mDatas, isStandard)
        // 自定义的Item的点击事件
        adapter?.setOnItemClickListener(this)
        recyclerview.adapter = adapter
    }

    private fun showGridView(isStandard: Boolean, isReverse: Boolean) {
        // 这里的4 横着滑动的话 表示4行 竖着滑动的话 表示4列
        recyclerview.layoutManager = GridLayoutManager(this, 5,
            if (isStandard) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, isReverse)
        // 初始实现，未抽取父类 - version1
//        val gridViewAdapter = GridViewAdapter(this, mDatas!!, isStandard)
        // 抽取了共同父类，大部分是父类实现 - version2
//        adapter = RecyclerViewSub2Adapter(this, mDatas!!)
        // 抽取了共同父类，大部分是子类实现 - version3
        adapter = RvSub2Adapter(this, mDatas, isStandard)
        adapter?.setOnItemClickListener(this)
        recyclerview.adapter = adapter
    }

    private fun showStaggerView(isStandard: Boolean, isReverse: Boolean) {
        val staggerLayouManager = StaggeredGridLayoutManager(2,
            if (isStandard) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL)
        staggerLayouManager.reverseLayout = isReverse
        recyclerview.layoutManager = staggerLayouManager
        adapter = RvSub3Adapter(this, mDatas, isStandard)
        adapter?.setOnItemClickListener(this)
        recyclerview.adapter = adapter
    }

    private fun showMultiplyItem(isStandard: Boolean) {
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val multiplyItemAdapter = MultiplyItemAdapter(this, mDatas, isStandard)
        multiplyItemAdapter.setOnItemClickListener(this)
        recyclerview.adapter = multiplyItemAdapter
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "点击图片 - $position", Toast.LENGTH_SHORT).show()
    }
}