package com.zhr.recyclerviewtest.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.bean.ItemBean

abstract class RvBaseAdapter(
    protected val context: Context?,
    protected val datas: List<ItemBean>?,
    protected val isStandard: Boolean,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }
        subBindViewHolder(holder, position)
    }

    abstract fun subBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }
}