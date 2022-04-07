package com.example.basecontent.p63top70

import io.reactivex.rxjava3.core.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63top69
 * @Author: zhr
 * @Date: 2022/3/30 22:08
 * @Version: V1.0
 */
interface UploadService {

    @POST("post")
    @Multipart
    fun upload(@Part file: MultipartBody.Part): Call<ResponseBody>

    @POST("post")
    @Multipart
    fun uploadMore(@Part files: List<MultipartBody.Part>): Call<ResponseBody>

    @Multipart
    @POST("post")
    fun uploadRecord(
        @PartMap inspectionBean: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imgs: List<MultipartBody.Part>,
        @Part videos: List<MultipartBody.Part>
    ): Call<ResponseBody>

    @GET
    @Streaming // 下载最好加上Steaming注解 避免内存溢出的问题 oom
    fun download1(@Url downloadUrl: String): Call<ResponseBody>

    @GET   // 这里可以通过转换器，直接将 RequestBody 转换成一个Bean类，就不需要成为一个String类了
    @Streaming // 下载最好加上Steaming注解 避免内存溢出的问题 oom
    fun download2(@Url downloadUrl: String): Flowable<ResponseBody>
}