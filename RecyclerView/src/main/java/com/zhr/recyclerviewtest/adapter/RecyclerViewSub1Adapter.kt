package com.zhr.recyclerviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

class RecyclerViewSub1Adapter(context: Context?, datas: List<ItemBean>?) :
    RecyclerViewBaseAdapter(context, datas) {

    // 子类自己实现差异性部分 共同性部分在父类实现
    override fun getSubView(
        parent: ViewGroup,
        viewType: Int,
    ): View {
        return LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false)
    }

    class RecyclerViewBaseHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
        var textView: TextView = view.findViewById(R.id.tv)
    }
}