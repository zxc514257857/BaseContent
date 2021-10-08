package com.example.basecontent.p1top9

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.example.basecontent.R
import com.example.basecontent.databinding.ActivityP1Binding

/**
 * 内容包括：TextView、Button、EditText、ImageView、ProgressBar
 */
class P1Activity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityP1Binding>(this, R.layout.activity_p1)
        val viewModel = ViewModelProvider(this).get(PbVisiableViewModel::class.java)
        binding.viewModel = viewModel
        // 设置这个手动修改viewModel数据才会响应，否则不会响应
        binding.lifecycleOwner = this
        viewModel.pbVisiable.observe(this, Observer {
            LogUtils.i("pbVisiable:::$it")
        })


        // TextView 的shadow属性 ：先设置shadowColor 再设置shadowRadius 才会显示阴影效果
        // 跑马灯textview： singleLine、focusable是否可获取焦点、focusableInIouchMode控制视图在触摸模式下是否可以聚焦
        // ellipsize在哪里省略文本、marqueeRepeatLimit 跑马灯重复次数设置
        // 视频： https://www.bilibili.com/video/BV1qM4y1M7wS?p=4


        // Button 直接设置颜色是不生效的，需要将主题后面加上  .Bridge
        // 给Button设置background 这里需要设置的为drawable 文件选择器， backgroundTint 这里设置的为color 颜色选择器(tint是染色的意思)
        // Button 这里的drawable用代码写指的就是stateListDrawable
        // 点击 onClick  长按 onLongClick   触摸 onTouch（它是最底层的方法）
        binding.btn1.setOnClickListener {
            LogUtils.i("onClick")
            viewModel.pbVisiable.value = viewModel.pbVisiable.value != true
        }
        binding.btn1.setOnLongClickListener {
            LogUtils.i("onLongClick")
            false
        }
        // 建议添加  @SuppressLint("ClickableViewAccessibility") 注解 提醒和onClick和onLongClick的冲突问题
        binding.btn1.setOnTouchListener { view, motionEvent ->
            // 这里的action主要有三个 actionDown：0（按下） 、actionUp：1（抬起） 、actionMove：2（按下不抬起，一直移动会一直回调）
            LogUtils.i("onTouch: ${motionEvent.action}")
            false
        }
        // 如果这里返回的true表示事件onTouch消费掉了，事件不再向下传递了，onLongClick和onClick就不会接收到了
        // 如果想让onLongClick和onClick响应，则onTouch要返回false。
        // 同理：onLongClick返回true 事件就不传给onClick了，如果onClick要响应，则onLongClick要返回false
        // 一次普通点击会回调：onTouch：0（按下）、onLongClick、onTouch：1（抬起）、onClick（证明onClick是抬起触发） 如果移动则会多次回调onTouch：2


        // EditText 一些属性：
        // hint、textColorHint、inputType、drawablexxx(drawableLeft) 在指定方向添加图片、drawablepadding 图片和文字的间距设置


        // ImageView 一些属性：
        // src、scaleType(缩放类型)、adjustViewBounds(调整的View的界限，保证图片的宽高比，让图片在不留外边的情况下完全显示，比fitxy更好一点)
        // fitCenter 是默认值（把最长边都显示进来的显示方式）、fitStart是在fitCenter的基础上朝左上角靠、fitEnd是在fitCenter的基础上朝右下角靠
        // center 只显示原图内容大小的显示方式
        // centerCrop 把最短边显示进来，居中显示的显示方式
        // matrix、center、centerInside 都是在原图上处理，不会将原图进行拉伸
        // center是大小都不管；matrix是大了缩小，小了不管；centerInside也是大了缩小，小了不管


        // progressbar 一些属性：
        // max(进度条的最大刻度值)、progress(进度条的默认值)、style="?android:attr/progressBarStyleHorizontal" 进度条方式
        // indeterminate 表示无限循环的progressbar 不会显示具体的进度信息，只是一个安慰剂的作用
    }
}


