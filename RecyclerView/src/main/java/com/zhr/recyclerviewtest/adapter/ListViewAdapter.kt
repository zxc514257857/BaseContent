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

class ListViewAdapter(
    private val context: Context?,
    private val datas: ArrayList<ItemBean>?,
) :
    RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {

    private val TAG: String = "ListViewAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        // item中使用了cardview 为了造出item间隔效果
        return ListViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.listview_item, parent, false))
        // 这样的打气筒 只能引入部分的view 不能展示整页view的效果
//        return ListViewHolder(View.inflate(context, R.layout.listview_item, null))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // 可能这样加载图片 比较耗费性能 程序容易崩溃
        holder.imageView.setImageResource(datas?.get(position)?.pic!!)
        holder.textView.text = datas[position].des
    }

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
        var textView: TextView = view.findViewById(R.id.tv)
    }
}