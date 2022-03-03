package com.zhr.recyclerviewtest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

// 创建一个抽象方法，让子类可以去继承
abstract class RecyclerViewBaseAdapter(
    protected val context: Context?,
    protected val datas: List<ItemBean>?,
) : RecyclerView.Adapter<RecyclerViewBaseAdapter.RecyclerViewBaseHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewBaseHolder {
        val view: View = getSubView(parent, viewType)
        return RecyclerViewBaseHolder(view)
    }

    // 因为布局是不同的 需要抽象出来抽象方法 让子类去实现
    abstract fun getSubView(
        parent: ViewGroup,
        viewType: Int,
    ): View

    override fun onBindViewHolder(
        holder: RecyclerViewBaseHolder,
        position: Int,
    ) {
        holder.imageView.setImageResource(datas?.get(position)?.pic!!)
        holder.textView.text = datas[position].des
        // 通过获取到itemview设置点击时间 把这个点击事件传递给itemClickListener()
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return datas?.size!!
    }

    class RecyclerViewBaseHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
        var textView: TextView = view.findViewById(R.id.tv)
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }
}