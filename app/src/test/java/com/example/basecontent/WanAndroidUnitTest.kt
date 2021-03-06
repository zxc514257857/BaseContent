package com.example.basecontent

import com.example.basecontent.p63top70.UploadService
import com.example.basecontent.p63top70.WanAndroidService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent
 * @Author: zhr
 * @Date: 2022/3/27 13:46
 * @Version: V1.0
 */
class WanAndroidUnitTest {

    private var retrofit: Retrofit? =
        Retrofit.Builder().baseUrl("https://www.wanandroid.com").build()
    private var service: WanAndroidService? = retrofit?.create(WanAndroidService::class.java)

    @Test
    fun loginTest() {
        service?.login("15527962751", "P@ssw0rd")?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    println("onResponse: ${response.body()?.string()}")
                } else {
                    println("onFailure: ")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println("onFailure: ${t.message}")
            }
        })
    }

    private var uploadService: UploadService? = null

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://www.httpbin.org/").build()
        uploadService = retrofit.create(UploadService::class.java)
    }


    @Test
    fun uploadFileTest() {
        val part = MultipartBody.Part.createFormData(
            "file1", "1.txt",
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                File("C:\\Users\\Administrator\\Desktop\\1.txt")
            )
        )
        val call = uploadService?.upload(part)
        // files: {file1: tjp I love you}
        // ?????????file1???????????????1.txt???????????????????????????tjp I love you
        println(call?.execute()?.body()?.string())
    }

    @Test
    fun uploadFilesTest() {
        val call = uploadService?.uploadMore(mutableListOf<MultipartBody.Part>().apply {
            add(
                MultipartBody.Part.createFormData(
                    "file1", "1.txt",
                    RequestBody.create(
                        "text/plain".toMediaTypeOrNull(),
                        File("C:\\Users\\Administrator\\Desktop\\1.txt")
                    )
                )
            )
            add(
                MultipartBody.Part.createFormData(
                    "file2", "2.txt",
                    RequestBody.create(
                        "text/plain".toMediaTypeOrNull(),
                        File("C:\\Users\\Administrator\\Desktop\\2.txt")
                    )
                )
            )
            add(
                MultipartBody.Part.createFormData(
                    "file3", "3.txt",
                    RequestBody.create(
                        "text/plain".toMediaTypeOrNull(),
                        File("C:\\Users\\Administrator\\Desktop\\3.txt")
                    )
                )
            )
        })
        // ?????????????????? file1???tjp I love you???   file2???tjp ???????????????   file3???tjp ????????????
        println(call?.execute()?.body()?.string())
    }

    @Test
    fun uploadRecord() {
        val map = HashMap<String, RequestBody>()
        map["id"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "666")
        map["content"] = RequestBody.create("text/plain".toMediaTypeOrNull(), "888")

        val call = uploadService?.uploadRecord(
            map,
            mutableListOf<MultipartBody.Part>().apply {
                add(
                    MultipartBody.Part.createFormData(
                        "imgs1", "1.txt",
                        RequestBody.create(
                            "*/*".toMediaTypeOrNull(),
                            File("C:\\Users\\Administrator\\Desktop\\1.txt")
                        )
                    )
                )
                add(
                    MultipartBody.Part.createFormData(
                        "imgs2", "2.txt",
                        RequestBody.create(
                            "*/*".toMediaTypeOrNull(),
                            File("C:\\Users\\Administrator\\Desktop\\2.txt")
                        )
                    )
                )
            },
            mutableListOf<MultipartBody.Part>().apply {
                add(
                    MultipartBody.Part.createFormData(
                        "videos1", "2.txt",
                        RequestBody.create(
                            "*/*".toMediaTypeOrNull(),
                            File("C:\\Users\\Administrator\\Desktop\\2.txt")
                        )
                    )
                )
                add(
                    MultipartBody.Part.createFormData(
                        "videos2", "3.txt",
                        RequestBody.create(
                            "*/*".toMediaTypeOrNull(),
                            File("C:\\Users\\Administrator\\Desktop\\3.txt")
                        )
                    )
                )
            }
        )
        println(call?.execute()?.body()?.string())
    }

    @Test  // ????????????????????????
    fun downloadFileTest1() {
        val response =
            uploadService?.download1(
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com" +
                        "%2Fuploads%2Fblog%2F202109%2F06%2F20210906225922_1c31b.thumb.1000_0.jpeg"
            )
                ?.execute()
        if (response?.isSuccessful == true) {
            val ins = response.body()?.byteStream()
            val fos = FileOutputStream("C:\\Users\\Administrator\\Desktop\\a.jpeg")
            // ???????????????????????????
            var len: Int = 0
            val buffer = ByteArray(4096)
            // ???????????????????????????kotlin?????????????????????
            while (ins?.read(buffer).also {
                    if (it != null) {
                        len = it
                    }
                } != -1) {
                fos.write(buffer, 0, len)
            }
            ins?.close()
            fos.close()
        }
    }

    @Test
    fun downloadFileTest2() {
        // ??????????????????????????????????????????
        uploadService?.download2(
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com" +
                    "%2Fuploads%2Fblog%2F202109%2F06%2F20210906225922_1c31b.thumb.1000_0.jpeg"
        )
            // ???????????????????????????kotlin?????????????????????
            // ????????? responseBody ??????File??????????????????????????????????????? subscribe?????????
            ?.map<File> { responseBody ->
                val ins = responseBody.byteStream()
                val file = File("C:\\Users\\Administrator\\Desktop\\a.jpeg")
                val fos = FileOutputStream(file)
                // ???????????????????????????
                var len: Int = 0
                val buffer = ByteArray(4096)
                // ???????????????????????????kotlin?????????????????????
                while (ins.read(buffer).also {
                        if (it != null) {
                            len = it
                        }
                    } != -1) {
                    fos.write(buffer, 0, len)
                }
                ins.close()
                fos.close()
                return@map file
            }
            ?.observeOn(Schedulers.io())
            ?.subscribeOn(AndroidSchedulers.mainThread())
            // ????????? responseBody ??????File??????????????????????????????????????? subscribe?????????
            ?.subscribe {}
    }

    @Test
    fun testGson() {

    }
}