package com.example.basecontent

import com.example.basecontent.p63top70.HttpbinService
import okhttp3.FormBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private var retrofit: Retrofit? = null
    private var service: HttpbinService? = null

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun postBody() {
        retrofit = Retrofit.Builder().baseUrl("https://www.httpbin.org").build()
        service = retrofit?.create(HttpbinService::class.java)
        val formBody = FormBody.Builder().add("a", "1").add("b", "2").build()
        val response = service?.postBody(formBody)?.execute()
        if (response?.isSuccessful == true) {
            println("onResponse: ${response.body()?.string()}")
        }
    }
}