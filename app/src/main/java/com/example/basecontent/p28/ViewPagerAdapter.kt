package com.example.basecontent.p28

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p28
 * @Author: zhr
 * @Date: 2022/2/28 23:04
 * @Version: V1.0
 */
class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {

    private var stringList: MutableList<String>? = null
    private var colorList: MutableList<Int>? = null

    constructor(stringList: MutableList<String>, colorList: MutableList<Int>) {
        this.stringList = stringList
        this.colorList = colorList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vp_item1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.textview.text = stringList?.get(position)
        holder.cl.setBackgroundColor(colorList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return stringList?.size!!
    }

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview = itemView.findViewById<TextView>(R.id.tv)
        val cl = itemView.findViewById<ConstraintLayout>(R.id.cl)
    }
}