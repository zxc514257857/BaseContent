package com.example.basecontent.p89top97

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p89
 * @Author: zhr
 * @Date: 2022/4/15 15:22
 * @Version: V1.0
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private var data: List<String>? = null
    private var context: Context? = null

    constructor(data: List<String>, context: Context) {
        this.data = data
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = TextView(context)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin = 18
        textView.layoutParams = layoutParams
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        (holder.itemView as TextView).text = data?.get(position)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}