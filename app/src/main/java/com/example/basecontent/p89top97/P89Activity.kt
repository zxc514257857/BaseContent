package com.example.basecontent.p89top97

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p89
 * @Author: zhr
 * @Date: 2022/4/14 15:12
 * @Version: V1.0
 */
class P89Activity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p89)

        findViewById<Button>(R.id.btn_intent1).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent2).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent3).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent4).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent5).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent6).setOnClickListener(this)
        findViewById<Button>(R.id.btn_intent7).setOnClickListener(this)

        requestPermission()
    }

    private fun intentTest1() {
        Intent().let {
            it.setClass(this, Activity1::class.java)
            it.putExtra("name", "zhr")
            it.putExtra("age", 18)
            startActivity(it)
        }
    }

    private fun intentTest2() {
        val bundle = Bundle()
        bundle.putString("name", "zhr")
        bundle.putInt("age", 19)
        Intent().let {
            // 封装Bundle对象
            it.setClass(this, Activity1::class.java)
            it.putExtras(bundle)
            startActivity(it)
        }
    }

    private fun intentTest3() {
        val student = Student("zhr", 20)
        Intent().let {
            it.setClass(this, Activity1::class.java)
            it.putExtra("student", student)
            startActivity(it)
        }
    }

    private fun intentTest4() {
        val student = Student1("zhr", 21)
        Intent().let {
            it.setClass(this, Activity1::class.java)
            it.putExtra("student", student)
            startActivity(it)
        }
    }

    private fun intentTest5() {
        startActivity(Intent(this, MediaRecordActivity::class.java))
    }

    private fun intentTest6() {
//        startActivity(Intent(this, MediaPlayerActivity::class.java))
        startActivity(Intent(this, VideoViewActivity::class.java))
    }

    private fun intentTest7() {
        startActivity(Intent(this, SoundPoolActivity::class.java))
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
                REQUEST_CODE
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_intent1 -> intentTest1()  // intent传递基本数据类型
            R.id.btn_intent2 -> intentTest2()  // intent传递bundle
            R.id.btn_intent3 -> intentTest3()  // intent传递实现serializable接口的对象
            R.id.btn_intent4 -> intentTest4()  // intent传递实现parcelable接口的对象
            R.id.btn_intent5 -> intentTest5()  // 视频录制
            R.id.btn_intent6 -> intentTest6()  // 视频播放
            R.id.btn_intent7 -> intentTest7()  // 音频播放
        }
    }
}