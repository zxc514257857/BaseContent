package com.example.basecontent.p28top43

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.basecontent.AppConstant
import com.example.basecontent.R

/**
 * 内容包括：Fragment的使用
 * 1、Fragment的展示和切换
 * 2、Fragment的通信
 * Activity传递数据给Fragment（通过Fragment().setArguments(bundle)）
 * Activity传递数据给Fragment（通过Fragment().setOnActivityCallback(return xxx) 返回值进行传值）
 * Fragment传递数据给Activity（通过fragment.sendData2Activity(xxx) 通过自定义的设值方法进行传值）
 * -因为只有在Activity中创建Fragment对象，所以只能在Fragment中创建回调，在Activity使用和调用回调
 *  在声明回调的类中，设置值。在设置回调的类中取值
 * -接口回调的方式可以抽象概括为eventbus 或者liveData方式
 * Fragment之间的数据传递（通过setArguments 和getArguments进行数据传参）
 * 3、Fragment生命周期：onAttach  onCreate  onCreateView  onActivityCreated | onStart  onResume  onPause  onStop
 *                   | onDestoryView   onDestory   onDetach
 * 打开Fragment界面 -> onAttach  onCreate  onCreateView  onActivityCreated  onStart  onResume
 * 点击Home键 -> onPause  onStop
 * 重新打开界面 -> onStart  onResume
 * 点击返回键 -> onPause  onStop  onDestoryView  onDestory  onDetach
 *
 *
 */
class P28Activity : AppCompatActivity() {

    private val TAG = "P28Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 静态添加的Fragment
//        setContentView(R.layout.activity_p28)
        setContentView(R.layout.activity_p28_1)

        // Fragment 碎片  Activity 活动
        // Fragment的特点：1、具有自己的生命周期  2、必须委托于Activity才能运行（必须有一个宿主Activity）

        // 动态切换的Fragment
        findViewById<Button>(R.id.btn1).setOnClickListener {
//            changeFragment(Fragment1())

            // Fragment和Activity之间传值   通过setArguments(bundle)进行传值
            // 传递String对象  Activity发送数据给Fragment
//            val bundle = Bundle()
//            bundle.putString(AppConstance.BUNDLE1, "20220116")
//            Fragment1().let {
//                it.arguments = bundle
//                changeFragment(it)
//            }

            Fragment1().let {
                // Fragment通过接口回调的方式 发送值给Activity
                it.setFragmentCallback(object : IFragmentCallback<Any> {
                    override fun sendData2Activity(data: Any) {
                        Log.e(TAG, "sendData2Activity: ${data as String}")
                    }
                })
                changeFragment(it)
            }
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
//            changeFragment(Fragment2())

            // Fragment和Activity之间传值
            // 传递实现了Parcelable 的对象   Activity发送数据给Fragment
            val bundle = Bundle()
            val bundle2Bean = Bundle2Bean().apply {
                this.name = "zhr"
                this.age = 18
                bundle.putParcelable(AppConstant.BUNDLE2, this)
            }
            Fragment2().let {
//                it.arguments = bundle

                // 通过接口回调的方式传值  也可以通过主动设置值的方式
                it.setActivityCallback(object : IActivityCallback<Any>{
                    override fun sendData2Fragment(): Any {
                        return bundle2Bean
                    }
                })
                changeFragment(it)
            }
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