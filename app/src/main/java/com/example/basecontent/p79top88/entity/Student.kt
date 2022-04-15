package com.example.basecontent.p79top88.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72
 * @Author: zhr
 * @Date: 2022/4/10 21:37
 * @Version: V1.0
 */
@Entity   // 创建了一个Student表  跟写bean类差不多
class Student {

    // 主键唯一 自动增长
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name: String = ""
    var age: Int = 0

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "Student(id=$id, name='$name', age=$age)"
    }
}