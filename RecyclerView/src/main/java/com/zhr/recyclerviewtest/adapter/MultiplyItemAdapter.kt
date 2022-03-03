package com.zhr.recyclerviewtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhr.recyclerviewtest.R
import com.zhr.recyclerviewtest.bean.ItemBean

class MultiplyItemAdapter(
    context: Context,
    datas: List<ItemBean>?, isStandard: Boolean,
) :
    RvBaseAdapter(context, datas, isStandard) {

    // 全图片的item
    private val TYPE_IMAGE_FULL: Int = 111

    // 左侧文字 右侧图片的item
    private val TYPE_IMAGE_RIGHT: Int = 222

    // 上面文字 下面三个图片的item
    private val TYPE_IMAGE_BOTTOM: Int = 333

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        if (viewType == TYPE_IMAGE_FULL) {
            view = LayoutInflater.from(context).inflate(R.layout.multiply_type1_item, parent, false)
            return Type0ViewHolder(view)
        } else if (viewType == TYPE_IMAGE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.multiply_type2_item, parent, false)
            return Type1ViewHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.multiply_type3_item, parent, false)
            return Type2ViewHolder(view)
        }
    }

    override fun subBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = holder.itemViewType
        if (viewType == TYPE_IMAGE_FULL) {
            val type0ViewHolder = holder as Type0ViewHolder
            type0ViewHolder.imageView.setImageResource(datas?.get(position)?.pic!!)
        } else if (viewType == TYPE_IMAGE_RIGHT) {
            val type1ViewHolder = holder as Type1ViewHolder
            type1ViewHolder.textView.text = datas?.get(position)?.des
            type1ViewHolder.imageView.setImageResource(datas?.get(position)?.pic!!)
        } else {
            val type2ViewHolder = holder as Type2ViewHolder
            type2ViewHolder.textView.text = datas?.get(position)?.des
            val pic = datas?.get(position)?.pic
            type2ViewHolder.imageView1.setImageResource(pic!!)
            type2ViewHolder.imageView2.setImageResource(pic)
            type2ViewHolder.imageView3.setImageResource(pic)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = datas?.get(position)?.type
        if (type == 0) {
            return TYPE_IMAGE_FULL
        } else if (type == 1) {
            return TYPE_IMAGE_RIGHT
        } else {
            return TYPE_IMAGE_BOTTOM
        }
    }

    private class Type0ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.iv)
    }

    private class Type1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.tv)
        var imageView: ImageView = view.findViewById(R.id.iv)
    }

    private class Type2ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.tv)
        var imageView1: ImageView = view.findViewById(R.id.iv1)
        var imageView2: ImageView = view.findViewById(R.id.iv2)
        var imageView3: ImageView = view.findViewById(R.id.iv3)
    }
}