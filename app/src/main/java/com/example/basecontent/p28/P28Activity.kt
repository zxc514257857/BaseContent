package com.example.basecontent.p28

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.basecontent.R

/**
 * 内容包括：Fragment的使用
 */
class P28Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_p28)
        setContentView(R.layout.activity_p28_1)

        // Fragment 碎片  Activity 活动
        // Fragment的特点：1、具有自己的生命周期  2、必须委托于Activity才能运行（必须有一个宿主Activity）

        findViewById<Button>(R.id.btn1).setOnClickListener {
            changeFragment(Fragment1())
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            changeFragment(Fragment2())
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        // replace commit    add hide show commit
        transaction.replace(R.id.framelayout, fragment)
        // 将fragment添加到回退栈 如果不写，点击返回会一下子Activity全部退出  写了的话，点击返回会一个个fragment退出栈
        transaction.addToBackStack(null)
        transaction.commit()
    }
}