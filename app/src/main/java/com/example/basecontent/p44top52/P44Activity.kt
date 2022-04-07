package com.example.basecontent.p44top52

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p44
 * @Author: zhr
 * @Date: 2022/3/13 14:00
 * @Version: V1.0
 */
class P44Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p44)
        // activity的生命周期函数 看调用的结果
        // activity中打开fragment，activity的生命周期会怎么走，fragment的生命周期会怎么走

        // startService 和bindService
        bindService()

        // broadcast 广播接收者 静态注册的广播接收者 和动态注册的广播接收者
        // 在清单文件中注册的广播 - 静态注册广播（清单文件注册，代码发送 startReceiver  sendBroadcast）
        // 在代码中注册的广播 - 动态注册的广播（代码注册，代码发送 registerReceiver  sendBroadcast）

    }

    fun bindService() {
        bindService(Intent(this, P44Service::class.java), serviceConn, Context.BIND_AUTO_CREATE)
    }

    fun unbindService() {
        unbindService(serviceConn)
    }

    private val serviceConn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService()
    }
}