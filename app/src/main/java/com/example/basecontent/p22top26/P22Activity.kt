package com.example.basecontent.p22top26

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.LogUtils
import com.example.basecontent.R
import com.example.basecontent.databinding.ActivityP22Binding

/**
 * 内容包括：帧动画（AnimationDrawable）、补间动画（Animation）、属性动画（Animator）
 * 单位和尺寸（dp、px）、ViewPager
 */
class P22Activity : AppCompatActivity() {

    // 默认false 停止播放
    private var playFlag1: Boolean = false
    private var playFlag2: Boolean = false
    private var playFlag3: Boolean = false

    @SuppressLint("Recycle", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityP22Binding>(this, R.layout.activity_p22)

        // 帧动画：由一帧一帧的动画文件循环播放产生的动画  res -> drawable -> animation-list -> AnimationDrawable
        binding.btn1.setOnClickListener {
            LogUtils.i("点击开始帧动画")
            // 如果这里不再设置一下背景会报错：BitmapDrawable cannot be cast to AnimationDrawable
            binding.iv2.setBackgroundResource(R.drawable.frame_anim)
            // 根据background获取Drawable
            val animDrawable = binding.iv2.background as AnimationDrawable
            // 设置是否只播放一次 默认为false（循环播放）
            animDrawable.isOneShot = false
            if (playFlag1) {
                // 停止播放
                animDrawable.stop()
                playFlag1 = false
            } else {
                // 开始播放
                animDrawable.start()
                playFlag1 = true
            }
        }


        // 补间动画：我们指定动画的初始值和结束值，并设置变化时间，由android系统自动补全的这个动画就叫补间动画
        // 有四个重要的属性：透明（alpha）、旋转（rotate）、缩放（scale）、平移（translate）
        // res -> anim -> set -> AnimationUtils -> View.startAnimation()
        // 补间动画 里面的duration 没有自动补全功能  需要自己手动填写
        binding.btn2.setOnClickListener {
            //  以布局的方式创建补间动画
            //  val animId: Int = R.anim.alpha_anim  // 透明度动画
            //  val animId: Int = R.anim.rotate_anim  // 旋转动画
            //  val animId: Int = R.anim.scale_anim  // 缩放动画
            //  val animId: Int = R.anim.translate_anim  // 平移动画
            //  val animation = AnimationUtils.loadAnimation(this, animId)

            // 以代码的方式创建补间动画
            val animation = AlphaAnimation(1f,
                0f) // AlphaAnimation、RotateAnimation、ScaleAnimation、TranslateAnimation
            // 设置动画插值器
            // 匀速
//            val interpolator = LinearInterpolator()
            // 先慢后快
//            val interpolator = AccelerateInterpolator()
            // 开始回弹效果
//            val interpolator = AnticipateInterpolator()
            // 结束回弹效果
//            val interpolator = BounceInterpolator()
            // 跳一跳效果
//            val interpolator = CycleInterpolator(2f)
            // 动画结束时向前弹一定距离再回到原来位置
//            val interpolator = OvershootInterpolator(1f)
            // 系统默认的动画效果，先加速后减速
//            val interpolator = AccelerateDecelerateInterpolator()
            // 开始之前向前甩，结束的时候向后甩
//            val interpolator = AnticipateOvershootInterpolator()
            // 开始加速再减速
            val interpolator = DecelerateInterpolator()
            animation.interpolator = interpolator
            animation.duration = 2000
            // 这两个属性在anim xml文件中也可以设置，在set节点下设置
            animation.repeatMode = Animation.REVERSE
            animation.repeatCount = Animation.INFINITE
            binding.iv1.startAnimation(animation)
            if (playFlag2) {
                // 停止播放
                animation.cancel()
                playFlag2 = false
            } else {
                // 开始播放
                animation.start()
                playFlag2 = true
            }
            // 补间动画播放监听
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    LogUtils.i("补间动画开始播放")
                }

                override fun onAnimationEnd(p0: Animation?) {
                    LogUtils.i("补间动画播放结束")
                }

                override fun onAnimationRepeat(p0: Animation?) {
                    LogUtils.i("补间动画开始重复播放")
                }
            })
        }


        // 属性动画：ValueAnimator、ObjectAnimator
        // ValueAnimator 设置开始值和结束值，在规定时间内进行变化的过程
        val valueAnimator = ValueAnimator.ofFloat(0f, 5f)
        valueAnimator.duration = 2000
        // 获取值的变化监听
        valueAnimator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            LogUtils.i("valueAnimator: $value")
        }
        // 开启动画
        valueAnimator.start()


        // ObjectAnimator  继承至ValueAnimator   区别是ObjectAnimator 是可以针对View进行设置
        binding.btn3.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(binding.iv1, "alpha", 0f, 1f)
            objectAnimator.duration = 2000
            objectAnimator.repeatMode = ValueAnimator.REVERSE
            objectAnimator.repeatCount = ValueAnimator.INFINITE
            if (playFlag3) {
                // 停止播放  cancle 不起作用 奇了怪了....
                LogUtils.i("停止属性动画")
                objectAnimator.cancel()
                playFlag3 = false
            } else {
                // 开始播放
                LogUtils.i("开始属性动画")
                objectAnimator.start()
                playFlag3 = true
            }
            objectAnimator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    LogUtils.i("开始属性动画")
                }

                override fun onAnimationEnd(p0: Animator?) {
                    LogUtils.i("属性动画结束")
                }

                override fun onAnimationCancel(p0: Animator?) {
                    LogUtils.i("属性动画停止")
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    LogUtils.i("属性动画重复")
                }
            })
            // 这个监听和上面的等同；上面的是都必须全部实现；这个是可选实现
            objectAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationRepeat(animation: Animator?) {
                    super.onAnimationRepeat(animation)
                    LogUtils.i("属性动画重复")
                }
            })
        }


        // px像素：1920*1080的意思是，横排有1920个像素点，竖排有1080个像素点，它是一个不受手机大小影响的单位
        // 比如一个按钮在不同尺寸的手机上显示的大小相同，那么在大手机上就会相对显得很小，这是不对的；要在大手机上显示得也要很大才对，这才能适配上
        // 那么就需要一个单位能随着手机的大小也让按钮随之也变大小的单位dp
        // 设备越大，1dp所占用的像素值就越多


        // ViewPager
        // 实现的PageAdapter中少两个方法需要实现，要自己把它添加进去 instantiateItem 和 destroyItem
        val myVpAdapter = MyVpAdapter()
        val viewList = mutableListOf<View>()
        viewList.add(layoutInflater.inflate(R.layout.vp_item1, null))
        viewList.add(layoutInflater.inflate(R.layout.vp_item2, null))
        viewList.add(layoutInflater.inflate(R.layout.vp_item3, null))
        myVpAdapter.setData(viewList)
        binding.vp.adapter = myVpAdapter
    }
}