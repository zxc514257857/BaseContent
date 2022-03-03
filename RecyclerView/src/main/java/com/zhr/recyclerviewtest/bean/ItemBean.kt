package com.zhr.recyclerviewtest.bean

class ItemBean {

    var pic : Int? = null
    var des: String? = null
    var type: Int? = null

    override fun toString(): String {
        return "ItemBean(pic=$pic, des=$des, type=$type)"
    }
}