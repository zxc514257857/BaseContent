package com.example.basecontent.p89top97

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Button
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
 * @Date: 2022/4/14 16:58
 * @Version: V1.0
 */
class MediaPlayerActivity : AppCompatActivity(), View.OnClickListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private lateinit var btnRecord: Button
    private lateinit var textureView: TextureView
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        initView()
    }

    private fun initView() {
        // MediaPlayer 只能播放常见的视频格式 非常见的格式则不能使用MediaPlayer
        textureView = findViewById(R.id.textureView)
        btnRecord = findViewById(R.id.btn_record)
        btnRecord.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val text = btnRecord.text
        if (text.equals("开始播放")) {
            initPlayer()
            btnRecord.text = "结束播放"
        } else {
            stopPlayer()
            btnRecord.text = "开始播放"
        }
    }

    private fun initPlayer() {
        player = MediaPlayer()
        // 设置准备监听
        player?.setOnPreparedListener(this)
        // 设置播放完成监听
        player?.setOnCompletionListener(this)
        // 设置视频播放地址
        player?.setDataSource(File(getExternalFilesDir(""), "a.mp4").absolutePath)
        // 设置异步准备
        player?.prepareAsync()
//        player?.isLooping = true // 设置循环播放
        // 设置视频播放画布
        player?.setSurface(Surface(textureView.surfaceTexture))
        // 获取当前视频的总时间
        val duration = player?.duration
        // 获取当前视频的位置
        val curPos = player?.currentPosition
    }

    private fun stopPlayer() {
        player?.stop()
        player?.release()
    }

    // 如果准备完成
    override fun onPrepared(mp: MediaPlayer?) {
        // 则开始进行播放
        player?.start()
    }

    // 如果播放完成
    override fun onCompletion(mp: MediaPlayer?) {
        // 则停止播放
        player?.stop()
    }
}