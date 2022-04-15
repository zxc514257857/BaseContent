package com.example.basecontent.p71toP78.core

import com.example.basecontent.p71toP78.bean.ResponseResult
import com.example.basecontent.p71toP78.bean.SuccessBean
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p71.core
 * @Author: zhr
 * @Date: 2022/4/9 21:03
 * @Version: V1.0
 */
// 自定义Observable 在发送和接收流向数据时 对数据进行拦截
abstract class CustomObserver : Observer<ResponseResult> {

    abstract fun success(bean: SuccessBean)
    abstract fun error(msg: String)

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: ResponseResult) {
        if (t.data == null) { // 得到为null bean
            error(t.message + "请求失败，请联系管理员解决 0x01")
        } else { // 得到成功Bean
            t.data?.let {
                success(it)
            }
        }
    }

    override fun onError(e: Throwable) {
        error(e.message + "请求失败，请联系管理员解决 0x02")
    }

    override fun onComplete() {}
}