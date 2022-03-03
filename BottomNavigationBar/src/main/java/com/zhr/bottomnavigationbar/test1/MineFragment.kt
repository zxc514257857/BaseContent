package com.zhr.bottomnavigationbar.test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhr.bottomnavigationbar.R

class MineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    companion object {
        @JvmStatic
        fun getInstance(): Fragment {
            return MineFragment()
        }
    }
}