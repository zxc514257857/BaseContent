package com.zhr.bottomnavigationbar.test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhr.bottomnavigationbar.R

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    companion object{
        @JvmStatic
        fun getInstance(): Fragment{
            return SettingFragment()
        }
    }
}