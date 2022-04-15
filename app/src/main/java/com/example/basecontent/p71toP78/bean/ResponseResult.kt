package com.example.basecontent.p71toP78.bean

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p71
 * @Author: zhr
 * @Date: 2022/4/9 20:24
 * @Version: V1.0
 * 请求服务器的结果Bean  - 总Bean
 */
class ResponseResult {

    var code = 0
    var message = ""
    var data: SuccessBean? = null

    override fun toString(): String {
        return "ResponseResule(code=$code, message='$message', successBean=$data)"
    }
}

class SuccessBean {

    var id = 0
    var name = ""

    override fun toString(): String {
        return "SuccessBean(id=$id, name='$name')"
    }
}