package com.zhr.recyclerviewtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

class RvSub2Adapter(
    context: Context?,
    datas: List<ItemBean>?,
    isStandard: Boolean?,
) : RvBaseAdapter(context, datas, isStandard == true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RvSub2Holder(LayoutInflater.from(context)
            .inflate(R.layout.gridview_item, parent, false))
    }

    override fun subBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rvSub2Holder = holder as RvSub2Holder
        rvSub2Holder.imageView.setImageResource(datas?.get(position)?.pic!!)
        rvSub2Holder.textView.text = datas[position].des
        rvSub2Holder.cardView.layoutParams
        var layoutParams: FrameLayout.LayoutParams? = null
        // 如果垂直滑动时 宽度怼满 这里的LayoutParams 传入的是父布局
        if (isStandard) {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT)
        } else {
            // 如果水平滑动时 高度怼满
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        }
        rvSub2Holder.cardView.layoutParams = layoutParams
    }

    private class RvSub2Holder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
        var textView: TextView = view.findViewById(R.id.tv)
        var cardView: CardView = view.findViewById(R.id.cv)
    }
}