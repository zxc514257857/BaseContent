package com.example.basecontent.p89top97

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R
import java.io.File

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p89
 * @Author: zhr
 * @Date: 2022/4/14 18:28
 * @Version: V1.0
 */
class VideoViewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        // 如果想要更高定制化的Player的话，还是使用MediaPlayer。VideoView的UI都是固定好了的
        initView()
    }

    private fun initView() {
        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoPath(File(getExternalFilesDir(""), "a.mp4").absolutePath)
        // 创建媒体控制器
        val mediaController = MediaController(this)
        // 设置上一曲 下一曲的监听
        mediaController.setPrevNextListeners(this, this)
        videoView.setMediaController(mediaController)
        // 开始播放
        videoView.start()
    }

    override fun onClick(v: View?) {
        // TODO: 如何获取到MediaPlayer里面的next preview按钮
        Log.e("TAG", "onClick: ${v?.id}")
    }
}