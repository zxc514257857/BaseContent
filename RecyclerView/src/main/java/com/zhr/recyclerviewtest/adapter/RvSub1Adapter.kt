package com.zhr.recyclerviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

// 雷电模拟器 没连接上网络还不给运行程序
class RvSub1Adapter(
    context: Context?,
    datas: List<ItemBean>?,
    isStandard: Boolean?,
) : RvBaseAdapter(context, datas, isStandard == true) {

    private val TYPE_NORMAL: Int = 0
    private val TYPE_LOADMORE: Int = 1

    companion object {
        @JvmStatic
        val STATE_LOADING: Int = 0

        @JvmStatic
        val STATE_RELOAD: Int = 1

        @JvmStatic
        val STATE_NORMAL: Int = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_NORMAL) {
            return RvSub1Holder(LayoutInflater.from(context)
                .inflate(R.layout.listview_item, parent, false))
        } else {
            return RvSub1LoadmoreHolder(LayoutInflater.from(context)
                .inflate(R.layout.listview_loadmore_item, parent, false))
        }
    }

    override fun subBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = holder.itemViewType
        if (itemViewType == TYPE_NORMAL) {
            val rvSub1Holder = holder as RvSub1Holder
            rvSub1Holder.imageView.setImageResource(datas?.get(position)?.pic!!)
            rvSub1Holder.textView.text = datas[position].des
        } else {
            val rvSub1LoadmoreHolder = holder as RvSub1LoadmoreHolder
            rvSub1LoadmoreHolder.updateState(STATE_LOADING)
            rvSub1LoadmoreHolder.reload.setOnClickListener {
                // 上滑加载更多数据
                rvSub1LoadmoreHolder.updateState(STATE_LOADING)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        // 如果为条目的最后一个，返回条目类型为加载中条目(永远就会有最后一个条目数据加载不出来)
        if (position == itemCount - 1) {
            return TYPE_LOADMORE
            // 如果不为条目中的最后一个，返回条目类型为普通条目类型
        } else {
            return TYPE_NORMAL
        }
    }

    private var mOnSlideUpToRefreshListener: OnSlideUpToRefreshListener? = null

    interface OnSlideUpToRefreshListener {
        fun OnSlideUpToRefresh(rvSub1LoadmoreHolder: RvSub1LoadmoreHolder)
    }

    fun setOnSlideUpToRefreshListener(onSlideUpToRefreshListener: OnSlideUpToRefreshListener) {
        mOnSlideUpToRefreshListener = onSlideUpToRefreshListener
    }

    private inner class RvSub1Holder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
        var textView: TextView = view.findViewById(R.id.tv)
    }

    inner class RvSub1LoadmoreHolder(view: View) : RecyclerView.ViewHolder(view) {
        var loading: Group = view.findViewById(R.id.loading)
        var reload: TextView = view.findViewById(R.id.reload)

        fun updateState(state: Int) {
            loading.visibility = View.GONE
            reload.visibility = View.GONE
            when (state) {
                STATE_LOADING -> {
                    loading.visibility = View.VISIBLE
                    reload.visibility = View.GONE
                    // 上滑加载更多数据 （这里之前一直调用不起来，后面找出原因 是这个调用类必须写成inner class才可调用起来）
                    mOnSlideUpToRefreshListener?.OnSlideUpToRefresh(this)
                }
                STATE_RELOAD -> {
                    loading.visibility = View.GONE
                    reload.visibility = View.VISIBLE
                }
                STATE_NORMAL -> {
                    loading.visibility = View.GONE
                    reload.visibility = View.GONE
                }
                else -> {
                }
            }
        }
    }
}