package com.example.basecontent.p63top70

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.concurrent.thread

/**
 * @Des:
 * 1.OkHttp基本使用
 * 2.Retrofit基本使用
 * 3.Retrofit中的注解
 * 4.Retrofit中的转换器
 * 5.Retrofit中的嵌套请求和适配器（先请求一个接口，然后在获取到数据之后，再去请求另外一个接口，这就是接口的嵌套）
 * https://www.bilibili.com/video/BV1qM4y1M7wS?p=69
 * 6.文件上传与文件下载
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63
 * @Author: zhr
 * @Date: 2022/3/21 22:14
 * @Version: V1.0
 */
class P63Activity : AppCompatActivity() {

    var client: OkHttpClient? = null
    var retrofit: Retrofit? = null
    var httpbinService: HttpbinService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 同步请求，阻塞线程，等待结果返回
        // 异步请求，接口回调，回调通知
        setContentView(R.layout.activity_p63)
        client = OkHttpClient()

        // retrofit是对okhttp的封装，提供了比okhttp更加简单的使用方式
        // 1.根据http的接口创建java接口   2.创建Retrofit对象，并生成接口实现类对象

        retrofit = Retrofit.Builder().baseUrl("https://www.httpbin.org").build()
        httpbinService = retrofit?.create(HttpbinService::class.java)
        // retrofit的注解： 方法注解、标记注解、参数注解、其他注解
        // 方法注解：@get、@post、@delete、@put、@Http
        // 标记注解：@FormUrlEncoded、@Multipart、@Streaming
        // 参数注解：@Query、@QueryMap、@Body、@Field、@FieldMap、@Part、@PartMap
        // 其他注解：@Path、@Header、@Headers、@Url

        // 上传或下载文件   代码写在test里面，而不是AndroidTest里面，要不是还是要启动模拟器
    }

    fun getSync(view: View) {
        thread {
            val request = Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build()
            val call = client?.newCall(request)
            // 同步方法会阻塞后续代码执行，所以需要放在异步线程中
            val response = call?.execute()
            Log.e("TAG", "getSync: " + response?.body?.string())
        }
    }

    fun getAsync(view: View) {
        val request = Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build()
        val call = client?.newCall(request)
        // 异步方法不会阻塞后续代码执行，内部已经创建了子线程
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("TAG", "onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("TAG", "getAsync: " + response.body?.string())
                }
            }
        })
    }

    fun postSync(view: View) {
        thread {
            val formbody = FormBody.Builder().add("a", "1").add("b", "2").build()
            // okhttp默认创建出来的是get的请求，可以写可以不写，如果是post则需要写出来post()
            val request =
                Request.Builder().url("https://www.httpbin.org/post").post(formbody).build()
            val call = client?.newCall(request)
            val response = call?.execute()
            Log.e("TAG", "postSync: " + response?.body?.string())
        }
    }

    fun postAsync(view: View) {
        val formBody = FormBody.Builder().add("a", "1").add("b", "2").build()
        val request =
            Request.Builder().url("https://www.httpbin.org/post").post(formBody).build()
        val call = client?.newCall(request)
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("TAG", "onFailure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.e("TAG", "postAsync: " + response.body?.string())
                }
            }
        })
    }

    fun retrofitGet(view: View) {
        // retrofit通过注解，自动帮我们封装好了okhttp的一些操作
        val call = httpbinService?.getFun("zhr", "123456")
        call?.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "onResponse: ${response.body()?.string()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun retrofitPost(view: View) {
        thread {
            // execute 要加上子线程  enqueue就在子线程中运行
            val call = httpbinService?.postFun("tjp", "123456")
            val response = call?.execute()
            if (response?.isSuccessful == true) {
                Log.e("TAG", "onResponse: ${response.body()?.string()}")
            }
        }
    }

    fun queryMap(view: View) {
        httpbinService?.httpFun(HashMap<String, String>().apply {
            put("un", "tjp")
            put("pw", "123456")
        })?.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    Log.e("TAG", "onResponse: ${response.body()?.string()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun postBody(view: View) {
        thread {
            // body 是用于加请求体的
            // execute 要加上子线程  enqueue就在子线程中运行
            val formBody = FormBody.Builder().add("a", "1").add("b", "2").build()
            val response = httpbinService?.postBody(formBody)?.execute()
            if (response?.isSuccessful == true) {
                Log.e("TAG", "onResponse: ${response.body()?.string()}")
            }
        }
    }

    fun postPath(view: View) {
        thread {
            // https://www.httpbin.org/post
            // header 是用于加请求头的
            val response = httpbinService?.postPath(
                "post", "android", "zhr", "123456"
            )?.execute()
            if (response?.isSuccessful == true) {
                Log.e("TAG", "onResponse: ${response.body()?.string()}")
            }
        }
    }

    fun postHeaders(view: View) {
        thread {
            val response = httpbinService?.postHeaders()?.execute()
            if (response?.isSuccessful == true) {
                Log.e("TAG", "onResponse: ${response.body()?.string()}")
            }
        }
    }

    fun postUrl(view: View) {
        thread {
            // url 指定一个完整的http url地址作为请求路径
            val response = httpbinService?.postUrl("https://www.httpbin.org/post")?.execute()
            if (response?.isSuccessful == true) {
                Log.e("TAG", "onResponse: ${response.body()?.string()}")
            }
        }
    }

    fun wanAndroidTest1(view: View) {
        // 通过gson将jsonStr转换为javaBean对象
        val retrofit: Retrofit? = Retrofit.Builder().baseUrl("https://www.wanandroid.com").build()
        val service: WanAndroidService? = retrofit?.create(WanAndroidService::class.java)
        service?.login("15527962751", "P@ssw0rd")?.enqueue(object :
            retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val jsonString = response.body()?.string()
                    Log.e("TAG", "onResponse: $jsonString")
                    if (jsonString?.isNotEmpty() == true) {
                        val loginBean = Gson().fromJson(jsonString, LoginBean::class.java)
                        // 我们可以从Bean中取数据了
                        Log.e("TAG", "onResponse: ${Gson().toJson(loginBean)}")
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun wanAndroidTest2(view: View) {
        // 通过retrofit转换器，将jsonStr转换为javaBean对象
        val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: WanAndroidService? = retrofit?.create(WanAndroidService::class.java)
        service?.login1("15527962751", "P@ssw0rd")?.enqueue(object :
            retrofit2.Callback<LoginBean> {
            override fun onResponse(
                call: retrofit2.Call<LoginBean>,
                response: retrofit2.Response<LoginBean>
            ) {
                if (response.isSuccessful) {
                    val loginBean = response.body()
                    Log.e("TAG", "onResponse: ${Gson().toJson(loginBean)}")
                }
            }

            override fun onFailure(call: retrofit2.Call<LoginBean>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    // retrofit的适配器，retrofit的接口方法返回类型必须是Call
    // Rxjava能够非常优雅的解决retrofit接口回调的嵌套问题
    fun wanAndroidTest3(view: View) {
        // 先登录，再获取文章列表
        val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create()) // 添加转换器
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())  // 添加适配器
            .build()
        val service: WanAndroidService? = retrofit?.create(WanAndroidService::class.java)
        // 通过Flowable 可以使用链式调用
        service?.login2("15527962751", "P@ssw0rd")
            ?.observeOn(Schedulers.io()) // 在网络访问在子线程
            ?.subscribeOn(AndroidSchedulers.mainThread())  // 回调在主线程
            // 接口回调内容
            ?.subscribe(object : io.reactivex.rxjava3.functions.Consumer<LoginBean> {
                override fun accept(loginBean: LoginBean?) {
                    Log.e("TAG", "onNext: ${Gson().toJson(loginBean)}")
                }
            }, object : io.reactivex.rxjava3.functions.Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    Log.e("TAG", "onError: ${t?.message}")
                }
            }, object : Action {
                override fun run() {
                    Log.e("TAG", "onComplete: ")
                }
            })
    }

    fun wanAndroidTest4(view: View) {
        val cookies: MutableMap<String, List<Cookie>> = mutableMapOf()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
                // 能够保存cookie的okhttp
            .callFactory(
                OkHttpClient.Builder()
                    .cookieJar(object : CookieJar {
                        override fun loadForRequest(url: HttpUrl): List<Cookie> {
                            val cookies = cookies[url.host]
                            return cookies ?: arrayListOf()
                        }

                        override fun saveFromResponse(url: HttpUrl, list: List<Cookie>) {
                            cookies[url.host] = list
                        }
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val service = retrofit.create(WanAndroidService::class.java)
        service.login2("15527962751", "P@ssw0rd")
            .flatMap {
                Log.e("TAG", "wanAndroidTest4: ${Gson().toJson(it)}")
                service.getArticle(0)
            }.observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.e("TAG", "wanAndroidTest4: ${Gson().toJson(it)}")
            }
    }
}