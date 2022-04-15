package com.example.basecontent.p71toP78.core

import com.example.basecontent.p71toP78.bean.ResponseResult
import com.example.basecontent.p71toP78.bean.SuccessBean
import io.reactivex.rxjava3.core.Observable

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p71.core
 * @Author: zhr
 * @Date: 2022/4/9 20:31
 * @Version: V1.0
 */
object LoginEngine {

    // 模拟后台接口发送数据的方法
    // Observable 数据接收
    fun login(name: String, pwd: String): Observable<ResponseResult> {
        val result = ResponseResult()
        if ("zhr".equals(name) && "123456".equals(pwd)) {  // 登录成功
            val bean = SuccessBean()
            bean.id = 0
            bean.name = "zhr登录成功"
            result.code = 200
            result.message = "登录成功"
            result.data = bean
        } else {  // 登录失败
            result.code = 404
            result.message = "登录失败"
            result.data = null
        }
        // 返回总Bean 数据  -> 发送
        return Observable.just(result)
    }
}