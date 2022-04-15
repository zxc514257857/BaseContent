package com.example.basecontent.p89top97

import android.media.SoundPool
import android.os.Build
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p89
 * @Author: zhr
 * @Date: 2022/4/14 18:34
 * @Version: V1.0
 */
class SoundPoolActivity : AppCompatActivity() {

    private lateinit var btn: Button
    private var list: ArrayList<SoundBean>? = null
    private var soundPool: SoundPool? = null

    override fun setContentView(view: View?) {
        super.setContentView(view)
        setContentView(R.layout.activity_sound_pool)

        // MediaPlayer适合播放时间较长，所占空间比较大的音频文件，不支持多个音频同时播放。这就决定的MediaPlayer在某些场景下就不太适合
        // SoundPool适合播放密集急促而短暂的音效
        initView()
        initSoundPool()
    }

    private fun initView() {
        btn = findViewById(R.id.btn)
    }

    private fun initSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = SoundPool.Builder().setMaxStreams(6).build()
            list?.let {
//                it.add(SoundBean("aaa", soundPool.load(this, R.raw.aaa, 1)))
//                it.add(SoundBean("bbb", soundPool.load(this, R.raw.bbb, 1)))
//                it.add(SoundBean("ccc", soundPool.load(this, R.raw.ccc, 1)))
//                it.add(SoundBean("ddd", soundPool.load(this, R.raw.ddd, 1)))
            }
        }
        // 先load，然后play
//        soundPool.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        list?.forEach {
            // soundpool 卸载
//            soundPool?.unload()
        }
        soundPool?.release()
    }
}