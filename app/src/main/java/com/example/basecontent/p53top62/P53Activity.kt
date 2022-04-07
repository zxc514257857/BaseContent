package com.example.basecontent.p53top62

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p53
 * @Author: zhr
 * @Date: 2022/3/13 15:29
 * @Version: V1.0
 */
class P53Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p53)

        // 热修改，上传补丁包，无感知修复bug
        // 微信 Tinker热修复，支持dex，库和资源的更新，无需重新安装apk
        // 通过腾讯bugly服务集成tinker  https://bugly.qq.com/docs/
        // 使用大概教程 https://www.bilibili.com/video/BV1qM4y1M7wS?p=55
        // 在app-output-patch-release-patch_signed_7zip.apk 下发这个补丁包
        // 下发范围：开发设备（可以设置当前设备为开发设备true，release默认设置为false）、
        // 全量设备（所有设备）、自定义
        // 合成完apk之后会弹出 更新提示，检测到当前版本需要重启，是否重启应用？

        // 高德地图集成 地图关键字搜索及定位

        // Glide的占位符 placeholder、error、fallback 三个占位符  RequestOptions().placeholder().error().fallback().override()
        // .apply(requestOptions) 使用占位符
        // .transition(DrawableTransitionOptions.withCrossFade(factory))  交叉淡入效果（比较耗性能，在listview、recyclerview、gridview中谨慎使用）
        // .transform()  圆角 单独指定圆角 以及图片旋转
        // Glide默认是使用Drawable形式，如果是asBitmap 则使用的是Bitmap形式
        // load() 注意图片的格式是jpeg还是png格式的
        // Glide.with().load().apply(requestOptions).transition().transform().into()
        // GlideApp.with().load().placeholder().defaultImg().into()  // @GlideModule  AppGlideModule
        // @GlideExtension     Generated API使用


    }
}