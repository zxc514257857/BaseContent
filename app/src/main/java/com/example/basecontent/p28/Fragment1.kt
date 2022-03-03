package com.example.basecontent.p28

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.basecontent.AppConstant
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent
 * @Author: zhr
 * @Date: 2022/1/10 22:09
 * @Version: V1.0
 */
class Fragment1 : Fragment() {

    // 在这里输入logt 就可以出现TAG
    private val TAG = "Fragment1"
    private var rootView: View? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_p28_1, container, false)
            val textview = rootView?.findViewById<TextView>(R.id.tv)
            val button = rootView?.findViewById<Button>(R.id.btn)
            button?.setOnClickListener {
                textview?.text = "Fragment1"
                // 发送数据给Acctivity
                Bundle().apply {
                    this.putString(AppConstant.BUNDLE3, "20220116")
                    arguments = this
                }
            }
        }
        Log.e(TAG, "onCreateView: ")
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fragment的生命周期，先执行的是onCreate、再执行的是onCreateView
        Log.e(TAG, "onCreate: ")

        // 获取bundle发送的数据
        val bundle = arguments
        Log.e(TAG, "getBundle1: ${bundle?.getString(AppConstant.BUNDLE1)}")

        // 通过接口回调 Fragment发送数据给Activity
        fragmentCallback?.sendData2Activity("I am from Fragment1")
    }

    private var fragmentCallback: IFragmentCallback<Any>? = null

    fun setFragmentCallback(callback: IFragmentCallback<Any>) {
        this.fragmentCallback = callback
    }
}