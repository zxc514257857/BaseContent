package com.example.basecontent.p28

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p28
 * @Author: zhr
 * @Date: 2022/1/16 18:51
 * @Version: V1.0
 */
interface IFragmentCallback<T> {

    fun sendData2Activity(data: T)

}