package com.example.basecontent.p10

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.LogUtils
import com.example.basecontent.R
import com.example.basecontent.databinding.ActivityP10Binding

/**
 * 内容包括：Notification、ToolBar、AlertDialog、PopupWindow
 */
class P10Activity : AppCompatActivity() {

    private lateinit var manager: NotificationManager
    private lateinit var notification: Notification

    companion object {
        val channelId: String = "123"
        val channelName: String = "zhr"
        val notificationSendId: Int = 0
        val pendingRequestCode: Int = 0
        val pendingFlag: Int = 0

        // 通知的重要程度： NotificationManager.IMPORTANCE_NONE 关闭通知
        // NIN 开启通知，不会弹出，没有提示音，状态栏不显示
        // LOW 开启通知，不会弹出，没有提示音，(状态栏显示)
        // DEFAULT 开启通知，不会弹出，(有提示音，状态栏显示)
        // HIGH 开启通知，(会弹出，有提示音，状态栏显示)
        @RequiresApi(Build.VERSION_CODES.N) // 也是在android 8.0 以上使用
        val importance: Int = NotificationManager.IMPORTANCE_HIGH
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityP10Binding>(this, R.layout.activity_p10)

        // NotificationManager
        manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // android 8.0需要设置 通知渠道id
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance)
            manager.createNotificationChannel(channel)
        }
        // 记住PendingIntent这样的创建方式
        val intent = Intent(this, NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, pendingRequestCode, intent, pendingFlag)
        notification = NotificationCompat.Builder(this, channelId)
            // 最低需要设置这三个内容
            .setContentTitle("设置的标题")
            .setContentText("设置的文本内容")
            // 设置通知默认显示以及左上角显示的小图标
            // 这里的小图，不能是带有alpha图层的图片，即不能带颜色的图片
            .setSmallIcon(R.drawable.ic_baseline_apps_24)
            /****************************************************/
            // 设置通知右侧的大图标
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            // 设置小图标的颜色
            .setColor(Color.RED)
            // 设置点击通知后的跳转意图
            .setContentIntent(pendingIntent)
            // 点击之后是否取消 为true取消
            .setAutoCancel(true)
            // 设置通知被创建的时间（默认显示的就是当前时间） 如果有其他需求可以设置
            // 设置的 2020-02-02 22:22:22 的时间戳
            .setWhen(1580653342000L)
            .build()


        // ActionBar 在style文件中设置应用主题 NoActionBar
        // 自己添加ToolBar 代替ActionBar 的功能
        // ToolBar 一些属性：
        // layout_height="?actionBarSize"  高度使用actionBar的高度、navigationIcon 设置ToolBar左侧的图标，一般是返回箭头
        // title 设置ToolBar左侧的标题   titleTextColor 设置标题文本的颜色   titleMarginStart  设置标题距离左侧的间距
        // subtitle  设置子标题，在主标题下发显示的，和主标题一样，同为居左对齐   subtitleTextColor  设置子标题的文字颜色
        // logo  设置标题的logo 在icon右侧，title左侧显示
        // toolbar.setNavigationOnClickListener(xxx)  设置ToolBar icon的点击事件
        // ToolBar 注意不要导包倒错了，要导入androidx的包
        // ToolBar内设置文字居中的方法详见 xml文件中：注意text中的layout_gravity属性不能提示出来，需要手动敲出来


        // LinearLayout 一些属性
        // divider="xxx" 设置分割线的图片    showDividers： 设置分割线显示的位置  none无、middle每两个控件中间、begining控件开始，end控件结束
        // dividerPadding  设置分割线左右的padding值
        // 直接通过1dp 的View设置分割线 我们在项目中还使用得更多一点

    }

    fun click1(view: View) {
        LogUtils.i("点击发送通知")
        manager.notify(notificationSendId, notification)
    }

    fun click2(view: View) {
        LogUtils.i("点击取消发送通知")
        manager.cancel(notificationSendId)
    }

    fun click3(view: View) {
        // AlertDialog
        LogUtils.i("点击弹出AlertDialog")
        val dialogInflateView = layoutInflater.inflate(R.layout.activity_notification, null)
        AlertDialog.Builder(this)
            // 设置标题左侧的icon
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("我是标题")
            .setMessage("我是消息正文内容")
            // 设置自定义布局；布局的显示范围是指在正文内容以下，以及在按钮内容以上的部分 添加的View
            .setView(dialogInflateView)
            .setPositiveButton("确定") { dialogInterface, i ->
                LogUtils.i("点击了确定按钮")
            }
            .setNegativeButton("取消") { dialogInterface, i ->
                LogUtils.i("点击了取消按钮")
            }
            .setNeutralButton("左侧") { dialogInterface, i ->
                LogUtils.i("点击了左侧按钮")
            }
            // 这两个是收尾的，一定要放在最后面，顺序别弄错了
            .create()
            .show()
    }

    @SuppressLint("SetTextI18n")
    fun click4(view: View) {
        // PopupWindow  一般使用三个参数的或者四个参数的PopupWindow的构造方法
        val popupWindowInflateView = layoutInflater.inflate(R.layout.activity_notification, null)
        val textView = popupWindowInflateView.findViewById<TextView>(R.id.tv)
        val popupWindow = PopupWindow(popupWindowInflateView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        // 设置点击空白处是否退出popupwindow  为true退出  这个要在show之前设置；并且可以在上面的四参构造中设置
        popupWindow.isFocusable = true
        // 设置背景图片
        popupWindow.setBackgroundDrawable(ResourcesCompat.getDrawable(resources,
            R.mipmap.ic_launcher,
            null))
        // 设置子view的事件  在Inflater中findViewById然后再设置其他事件
        textView.setOnClickListener {
            textView.text = "我被点击了，我不干净了 555"
            // 关闭popupWindow
            popupWindow.dismiss()
        }
        // 设置加载动画   --- 不知道为什么设置的动画效果不生效
        popupWindow.animationStyle = R.style.popwindow_anim_style
        // 设置触摸使能
        popupWindow.isTouchable = true
        // 设置popupWindow外面的触摸使能
        popupWindow.isOutsideTouchable = true

        // 相对某个View的位置（正左下方），无偏移   这里传入的是按钮View的左下方
//        popupWindow.showAsDropDown(view)
        // 相对于某个View的位置，设置偏移    设置0，0默认的为左下角
        // x>0  往右偏移，但不会超出屏幕
        // x<0  往左偏移，但不会超出屏幕
        // y>0  往下偏移，但不会超出屏幕
        // y<0  往上偏移，但不会超出屏幕
        // 20,20  表示向右下角偏移
//        popupWindow.showAsDropDown(view, 100, 100)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {   // android 4.4版本以上使用
            // 相对于某个View的位置，设置偏移 End/Right 右下角  Start/Left 左下角  其他设置无效
            popupWindow.showAsDropDown(view, 0, 0, Gravity.TOP)
        }
    }
}






