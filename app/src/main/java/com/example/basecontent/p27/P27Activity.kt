package com.example.basecontent.p27

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * 内容包括：Mvvm
 */
class P27Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p27)

        // mvvm和mvp模式差不多，就是把Presenter换成了ViewModel，相对于Mvp来说
        // 1、简化了MVP，没有那么多的接口
        // 2、采用双向绑定的方式，ViewModel的数据变化立马反映到View，同样的，View的数据变化立马反映到ViewModel


    }
}