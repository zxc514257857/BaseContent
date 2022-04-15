package com.example.basecontent.p71toP78

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R
import com.example.basecontent.p71toP78.bean.SuccessBean
import com.example.basecontent.p71toP78.core.CustomObserver
import com.example.basecontent.p71toP78.core.LoginEngine
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 1.java普通对象和嵌套对象的序列化和反序列化
 * 2.array数组和list集合的序列化和反序列化
 * 3.Map和set集合的序列化和反序列化
 * 4.rx思维
 * 5.rx思维下载图片
 * 6.rxjava操作符学习
 * 7.自定义Observer
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63
 * @Author: zhr
 * @Date: 2022/3/21 22:14
 * @Version: V1.0
 */
class P71Activity : AppCompatActivity() {

    private val TAG: String = "P71Activity"
    private val PATH: String =
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202109%2F06%2F20210906225922_1c31b.thumb.1000_0.jpeg"
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p71)

        // JSON是一种轻量级的数据交换格式
        // Gson是Google提供的用来在java对象和JSON数据之间进行映射的java类库
        // http://github.com/google/gson

        // 完成java对象的序列化和反序列化(普通对象和嵌套对象)
        val user = User(18, "zhr", "1888888888", "132456")
        val job = Job("worker", "200")
        user.job = job
        // gson序列化
        val jsonStr1 = Gson().toJson(user)
        // gson反序列化
        val user1 = Gson().fromJson(jsonStr1, User::class.java)
        println("jsonStr1: $jsonStr1")   // jsonStr1: {"age":18,"name":"zhr","username":"1888888888","password":"132456","job":{"name":"worker","salary":"200"}}
        println("user1: $user1")   // user1: User(age=18, name=zhr, username=1888888888, password=132456, job=Job(name=worker, salary=200))

        // array数组的序列化
        val userArray = arrayOf(
            User(18, null, "1888888888", "1324561"),
            User(19, "zhr2", "1888888889", "1324562"),
            User(20, "zhr3", "1888888890", "1324563")
        )
        val usersStr = Gson().toJson(userArray)  // 如果字段为null的时候，在json字符串中不会显示
        val array = Gson().fromJson(usersStr, Array<User>::class.java)
        // usersStr:[{"age":18,"name":"zhr1","username":"1888888888","password":"1324561"},{"age":19,"name":"zhr2","username":"1888888889","password":"1324562"},{"age":20,"name":"zhr3","username":"1888888890","password":"1324563"}]
        println("usersStr:$usersStr")
        // array:User(age=18, name=zhr1, username=1888888888, password=1324561, job=null), User(age=19, name=zhr2, username=1888888889, password=1324562, job=null), User(age=20, name=zhr3, username=1888888890, password=1324563, job=null)
        println("array:" + array.joinToString {
            it.toString()
        })

        // list集合的序列化
        val list = listOf(
            User(18, null, "1888888888", "1324561"),
            User(19, "zhr2", "1888888889", "1324562"),
            User(20, "zhr3", "1888888890", "1324563")
        )
        val string = Gson().toJson(list)
//        val users = Gson().fromJson(string, List::class.java)  // 这样写是不行的  会有泛型擦除的问题  找不到具体对应的属性值
//        val users = Gson().fromJson(string, List<User>::class.java)  // 这样写也是语法不支持的
        val userList = Gson().fromJson<List<User>>(string, object : TypeToken<List<User>>() {}.type)
        println("string :$string")   // string :[{"age":18,"username":"1888888888","password":"1324561"},{"age":19,"name":"zhr2","username":"1888888889","password":"1324562"},{"age":20,"name":"zhr3","username":"1888888890","password":"1324563"}]
        println(
            "users :${
                userList.joinToString {
                    it.toString()
                }
            }"
        ) // users :User(age=18, name=null, username=1888888888, password=1324561, job=null), User(age=19, name=zhr2, username=1888888889, password=1324562, job=null), User(age=20, name=zhr3, username=1888888890, password=1324563, job=null)

        // map集合的序列化
        val map = mapOf(
            Pair("tjp1", User(18, null, "1888888888", "1324561")),
            Pair("tjp2", User(19, "zhr2", "1888888889", "1324562")),
            Pair("tjp3", User(20, "zhr3", "1888888890", "1324563"))
        )
        val mapStr = Gson().toJson(map)
        val userMap = Gson().fromJson<Map<String, User>>(
            mapStr,
            object : TypeToken<Map<String, User>>() {}.type
        )
        println("mapStr：$mapStr")   // mapStr：{"tjp1":{"age":18,"username":"1888888888","password":"1324561"},"tjp2":{"age":19,"name":"zhr2","username":"1888888889","password":"1324562"},"tjp3":{"age":20,"name":"zhr3","username":"1888888890","password":"1324563"}}
        println("userMap：$userMap")  // userMap：{tjp1=User(age=18, name=null, username=1888888888, password=1324561, job=null), tjp2=User(age=19, name=zhr2, username=1888888889, password=1324562, job=null), tjp3=User(age=20, name=zhr3, username=1888888890, password=1324563, job=null)}

        // 当gson的字段为null的时候，打印的时候不会显示出这个字段
        // 集合或者数组中的元素为null的时候，gson是不会忽略它的
        // gson中可以通过@SerializedName("") 给字段名进行重命名
        // gson中的 @Expose(serialize = false,deserialize = false) 指定某个属性是否参数与序列化和反序列化
        // serialize = false 指不参与序列化  deserialize = false 指不参与反序列化  默认都为true 即参与序列化和反序列化
        // 同时，如果想要让@Expose生效的话，必须使用这样创建 gson对象
        // 同时，使用transient 可以指定此属性不参与序列化和反序列化
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()  // 必须这样创建gson对象

        // rx编程思维下载图片 ，就是响应式编程，提高编程效率  -> rxjava  rxandroid
        // 什么是响应式编程：根据上一层的响应，来影响下一层的变化，从起点到终点
        // 起点（Observable被观察者）   终点（Observer观察者）
        // 在rxjava中 所有的函数都被称作 操作符
        val iv = findViewById<ImageView>(R.id.iv)
        val btn = findViewById<Button>(R.id.btn)
        // 起点： 这里的Observable 是导的rxjava包
        Observable.just(PATH)  // 这里发送的是String类型，后面默认的数据类型都是String类型的
            // 需求001：图片下载需求
            // 订阅： 关联起点和终点
            .subscribe(
                // 终点：
                object : Observer<String> {
                    // 表示订阅成功
                    override fun onSubscribe(d: Disposable) {}

                    // 表示上一层给我的响应
                    override fun onNext(t: String) {}

                    // 表示整个发送和接收的链条出现了异常
                    override fun onError(e: Throwable) {}

                    // 表示整个链条全部结束
                    override fun onComplete() {}
                })

        // TODO 执行的第二步
        Observable.just(PATH)
            // map就是把上一层类型转换为其他类型  将String转为Bitmap显示
            // TODO  执行的第三步
            .map(object : Function<String, Bitmap> {
                override fun apply(path: String?): Bitmap? {
                    try {
                        SystemClock.sleep(2000)
                        val okHttpClient = OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .build()
                        val request = Request.Builder().url(PATH).build()
                        val call = okHttpClient.newCall(request)
                        val response = call.execute()
                        if (response.isSuccessful) {
                            val ins = response.body?.byteStream()
                            val bitmap = BitmapFactory.decodeStream(ins)
                            // 把这个bitmap流向到下面一层
                            return bitmap
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    return null
                }
            })
            // 加了一个加水印的需求，用rxjava实现起来就比较简单
            .map(object : Function<Bitmap, Bitmap> {
                override fun apply(t: Bitmap?): Bitmap? {
                    // 加Bitmap水印的函数
//                    drawTextToBitmap()
                    return null
                }
            })
            // 加了一个需求，日志记录需求   bitmap没有做操作，可以直接流下去就可以了
            .map(object : Function<Bitmap, Bitmap> {
                override fun apply(t: Bitmap?): Bitmap? {
                    // 日志记录需求的函数
                    return null
                }
            })
//            .flatMap {
//            }
            // just 操作符 -> 发送数据从起点到终点
            // map 操作符 -> 进行数据发送过程中数据格式的转换
            // flatmap 操作符 -> 将集合或者数组中的数据压平、压实；以及将多个集合或者数组压成同一个集合数组

            // Observable 起点 ->  对事件进行加工变换（操作符） -> Observe 终点
            // 给上面的分配异步线程（图片下载操作）

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Bitmap> {
                // TODO 执行的第一步  打开加载框
                override fun onSubscribe(d: Disposable) {
                    Log.e("TAG", "onSubscribe: ")
                    progressDialog = ProgressDialog(this@P71Activity)
                    progressDialog?.setTitle("Rxjava run 正在加载中")
                    progressDialog?.show()
                }

                // TODO 执行的第四步  显示结果
                override fun onNext(t: Bitmap) {
                    Log.e("TAG", "onNext: ")
                    iv.setImageBitmap(t)
                }

                override fun onError(e: Throwable) {}

                // TODO 执行的最后一步  整个链条结束   关闭加载框
                override fun onComplete() {
                    Log.e("TAG", "onComplete: ")
                    progressDialog?.dismiss()
                }
            })

        // rxjava操作符学习
        rxjavaTest()

        // hci_snoopxxxxxx.cfa  蓝牙日志文件
        // 一些gradle脚本，左侧前面有可以点击的运行按钮进行点击运行执行 task

        // 自定义Observer
//        登录成功服务器返回
//        {
//            data:{xxxx 登录成功的Bean}
//            code:200
//            message:"登录成功"
//        }
//        登录失败服务器返回
//        {
//            data:null
//            code:404
//            message:"登录错误"
//        }
//        登录成功或者失败返回的数据格式不一样，导致的程序崩溃
        // retrofit 封装了 Request请求（OkHttp）和 Response请求（RxJava）
        btn.setOnClickListener {
            retrofitTest()
        }


    }

    private fun rxjavaTest() {
        // create操作符: just、map、flatmap
        // interval 操作符  给定时间间隔
        Observable.interval(1, TimeUnit.SECONDS).subscribe {
            println("zhr 大坏蛋")
        }
        // filter 根据条件进行过滤
        // distinct 过滤重复数据/去重
        // takeWhile  满足条件则终止
        // cast 强制转换数据类型
        // groupBy 根据某种条件进行分组
    }

    private fun retrofitTest() {
        val observable = LoginEngine.login("zhr", "123456")
        // 使用自己定义的CustomObserver 拦截了无用信息
        observable.subscribe(object : CustomObserver() {
            // 登录成功返回
            override fun success(bean: SuccessBean) {
                Log.e("TAG", "success: $bean")
            }
            // 登录失败返回
            override fun error(msg: String) {
                Log.e("TAG", "error: $msg")
            }
        })
    }
}

class User {

    var age: Int? = null
    var name: String? = null
    var username: String? = null
    var password: String? = null
    var job: Job? = null

    constructor(age: Int, name: String?, username: String, password: String) {
        this.age = age
        this.name = name
        this.username = username
        this.password = password
    }

    override fun toString(): String {
        return "User(age=$age, name=$name, username=$username, password=$password, job=$job)"
    }
}

data class Job(val name: String, val salary: String)