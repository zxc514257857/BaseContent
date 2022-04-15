package com.example.basecontent.p89top97

import android.hardware.Camera
import android.media.MediaRecorder
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
class MediaRecordActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnRecord: Button
    private lateinit var textureView: TextureView
    private var mediaRecorder: MediaRecorder? = null
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_record)
        initView()
    }

    private fun initView() {
        textureView = findViewById(R.id.textureView)
        btnRecord = findViewById(R.id.btn_record)
        btnRecord.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val text = btnRecord.text
        if (text.equals("开始录制")) {
            initRecord()
            btnRecord.text = "结束录制"
        } else {
            stopRecord()
            btnRecord.text = "开始录制"
        }
    }

    private fun initRecord() {

        camera = Camera.open()
        camera?.setDisplayOrientation(90)
        camera?.unlock()
        // 解决视频预览的颠倒问题
        mediaRecorder?.setCamera(camera)

        mediaRecorder = MediaRecorder()
        // 设置音频源 ：麦克风
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        // 设置视频源 ：摄像头  Camera1 用camera  Camera2 用Surface
        mediaRecorder?.setVideoSource(MediaRecorder.VideoSource.CAMERA)
        // 设置输出视频格式：MPEG4
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        // 设置音视频编码格式
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
        // 解决录制文件图像的颠倒问题
        mediaRecorder?.setOrientationHint(90)
        // 设置视频输出文件路径
        mediaRecorder?.setOutputFile(File(getExternalFilesDir(""), "a.mp4").absolutePath)
        // 设置视频大小
        mediaRecorder?.setVideoSize(640, 480)
        // 设置预览画面
        mediaRecorder?.setPreviewDisplay(Surface(textureView.surfaceTexture))
        // 进入准备阶段
        mediaRecorder?.prepare()
        // 开始录制
        mediaRecorder?.start()
    }

    private fun stopRecord() {
        // 结束录制
        mediaRecorder?.stop()
        // 释放录制
        mediaRecorder?.release()
        // 释放Camera
        camera?.stopPreview()
        camera?.release()
    }
}