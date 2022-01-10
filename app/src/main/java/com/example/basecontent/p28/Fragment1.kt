package com.example.basecontent.p28

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_p28_1, container, false)
        val textview = rootView.findViewById<TextView>(R.id.tv)
        val button = rootView.findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            textview.text = "Fragment1"
        }
        return rootView
    }
}