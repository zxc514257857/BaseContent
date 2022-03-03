package com.example.basecontent.p28

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p28
 * @Author: zhr
 * @Date: 2022/1/16 16:58
 * @Version: V1.0
 */
// 实现Parcelable  只用通过@Parcelize 并实现Parcelable即可
@Parcelize
class Bundle2Bean : Parcelable {

    var name: String? = null
    var age: Int? = null
}