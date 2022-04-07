package com.example.basecontent.p28top43

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
class Fragment2 : Fragment() {

    private val TAG = "Fragment2"
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
                textview?.text = "Fragment2"
            }
        }
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ")
        // 获取Bundle传递的数据
        val bundle = arguments
        val bundle2Bean = bundle?.getParcelable<Bundle2Bean>(AppConstant.BUNDLE2)
        Log.e(TAG, "name: ${bundle2Bean?.name},age: ${bundle2Bean?.age}")

        // 获取接口回调传递的数据  通过return发送的数据
        val data = activityCallback?.sendData2Fragment()
        val bundle2Bean1 = data as Bundle2Bean
        Log.e(TAG, "bundle2Bean: ${bundle2Bean1.name} ,,, ${bundle2Bean1.age}")
    }

    private var activityCallback: IActivityCallback<Any>? = null

    fun setActivityCallback(callback: IActivityCallback<Any>) {
        this.activityCallback = callback
    }
}