package com.example.basecontent.p63top70

import io.reactivex.rxjava3.core.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @Des: 创建服务接口
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63
 * @Author: zhr
 * @Date: 2022/3/26 22:23
 * @Version: V1.0
 */

// retrofit的转换器
// 之前的代码都是得到的是string类型或者是byteStream类型的数据，现在想要将字符串解析成JavaBean类型的对象
interface WanAndroidService {

    // 这个是没有使用转换器，泛型中是responseBody
    @POST("user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    // 这个是使用了retrofit转换器，泛型中是javaBean
    @POST("user/login")
    @FormUrlEncoded
    fun login1(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginBean>

    // 这个使用了rxandroid和rxjava3
    @POST("user/login")
    @FormUrlEncoded
    fun login2(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<LoginBean>

    @GET("lg/collect/list/{pageNum}/json")
    fun getArticle(@Path("pageNum") pageNum: Int): Flowable<ArticleData>
}