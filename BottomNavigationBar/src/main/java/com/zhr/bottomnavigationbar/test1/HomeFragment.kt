package com.zhr.bottomnavigationbar.test1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zhr.bottomnavigationbar.R
import java.util.*

class HomeFragment : Fragment() {

    private val TAG: String = "HomeFragment"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val tvHome = view.findViewById<TextView>(R.id.tvHome)
        val string = Random().nextInt(10).toString()
        tvHome.text = "Home$string"
        return view
    }

    companion object {
        @JvmStatic
        fun getInstance(): Fragment {
            return HomeFragment()
        }
    }
}