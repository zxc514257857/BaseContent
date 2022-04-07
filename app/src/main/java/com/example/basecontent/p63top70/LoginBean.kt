package com.example.basecontent.p63top70

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p63
 * @Author: zhr
 * @Date: 2022/3/27 20:43
 * @Version: V1.0
 */
class LoginBean : BaseResponse() {

    val data: LoginData? = null
}

data class LoginData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)