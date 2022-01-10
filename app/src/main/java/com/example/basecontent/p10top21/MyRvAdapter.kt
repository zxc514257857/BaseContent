package com.example.basecontent.p10top21

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basecontent.R

class MyRvAdapter : RecyclerView.Adapter<MyRvAdapter.InnerHolder>() {

    private lateinit var onRvItemClickListener: OnRvItemClickListener
    private lateinit var list: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRvAdapter.InnerHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_myrv, parent, false)
        // rootView.findViewById()
        // 不用从这里进行findViewById 直接从holder里面进行
        return InnerHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyRvAdapter.InnerHolder, position: Int) {
        holder.tv.text = list[position]
        holder.itemView.setOnClickListener {
            onRvItemClickListener.rvItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv = itemView.findViewById<TextView>(R.id.tv)!!
    }

    fun setData(list: MutableList<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    // 自定义item点击事件
    interface OnRvItemClickListener {
        fun rvItemClick(pos: Int)
    }

    fun setOnRvItemClickListener(onRvItemClickListener: OnRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener
    }
}