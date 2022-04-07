package com.example.basecontent.p63top70

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63
 * @Author: zhr
 * @Date: 2022/3/23 21:13
 * @Version: V1.0
 */
interface HttpbinService {

    // 返回值是Call类型的ResponseBody
//    @GET("get")
    @HTTP(method = "GET" , path = "get")  // https://www.httpbin.org/get?un=123&pw=456
    fun getFun(@Query("un") username: String, @Query("pw") password: String): Call<ResponseBody>

    // https://www.httpbin.org/post?un=username&pw=password
    @POST("post")
    @FormUrlEncoded
    fun postFun(@Field("un") username: String, @Field("pw") password: String): Call<ResponseBody>

    @HTTP(method = "GET", path = "get", hasBody = false)
    fun httpFun(
        @QueryMap map: Map<String, String>
    ): Call<ResponseBody>

    @POST("post")  // 传递json字符串以及 body类型的参数   这种和上面使用的区别是，这种需要自己创建body，上面是程序自动帮我们创建body
                        // body 不需要加 FormUrlEncoded 因为加了会默认是指表单的body，但这里的body是需要我们自己指定body
    fun postBody(@Body body: RequestBody): Call<ResponseBody>

    // 写死多个请求头
    @POST("{id}")
    @FormUrlEncoded  // @Field parameters can only be used with form encoding. (parameter #3)
    fun postPath(
        @Path("id") path: String,
        @Header("os") os: String,
        @Field("un") username: String,
        @Field("pw") password: String
    ): Call<ResponseBody>

    @Headers("os:android", "version:1.0")  // 写死在Header里面要传的参数
    @POST("post")
    fun postHeaders(): Call<ResponseBody>

    @POST   // @Post 加 Url 注解，指定一个完整的url，替换之前的baseurl
    fun postUrl(@Url url: String): Call<ResponseBody>
}