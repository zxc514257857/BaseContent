package com.example.basecontent.p1top9

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PbVisiableViewModel : ViewModel() {

    // 关心的内容：
    // pb1的显示隐藏
    val pbVisiable by lazy {
        val defaultVisiable = MutableLiveData<Boolean>()
        defaultVisiable.value = true
        defaultVisiable
    }

    // pb2的数值
    val pb2Value by lazy {
        val defaultPb2Value = MutableLiveData<Int>()
        defaultPb2Value.value = 8
        defaultPb2Value
    }

    fun onClick(){
        pb2Value.value = (pb2Value.value ?: 0) + 1
    }
}