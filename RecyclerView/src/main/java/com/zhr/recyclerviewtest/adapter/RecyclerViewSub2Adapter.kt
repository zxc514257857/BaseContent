package com.zhr.recyclerviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

class RecyclerViewSub2Adapter(context: Context?, datas: List<ItemBean>?) :
    RecyclerViewBaseAdapter(context, datas) {

    // 子类自己实现差异性部分 共同性部分在父类实现
    override fun getSubView(
        parent: ViewGroup,
        viewType: Int,
    ): View {
        return LayoutInflater.from(context).inflate(R.layout.gridview_item, parent, false)
    }
}