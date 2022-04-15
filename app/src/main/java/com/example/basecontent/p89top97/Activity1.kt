package com.example.basecontent.p89top97

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p89
 * @Author: zhr
 * @Date: 2022/4/14 15:15
 * @Version: V1.0
 */
class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p891)

        intentTest1()
        intentTest2()
        intentTest3()
        intentTest4()
    }

    private fun intentTest1() {
        val intent = intent
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        // 直接获取extra
        Log.e("TAG1", "name: $name , age: $age")
    }

    private fun intentTest2() {
        val intent = intent
        val bundle = intent.extras
        val name = bundle?.getString("name")
        val age = bundle?.getInt("age")
        // 或者直接获取Bundle都是可以的
        Log.e("TAG2", "name: $name , age: $age")
    }

    private fun intentTest3() {
        val intent = intent
        val student = intent.getSerializableExtra("student")?.let {
            it as Student
        }
        val name = student?.name
        val age = student?.age
        Log.e("TAG3", "name: $name , age: $age")
    }

    private fun intentTest4() {
        val intent = intent
        val student = intent.getParcelableExtra<Student1>("student")?.let {
            it
        }
        val name = student?.name
        val age = student?.age
        Log.e("TAG3", "name: $name , age: $age")
    }
}