package com.example.basecontent.p79top88.dao

import androidx.room.*
import com.example.basecontent.p79top88.entity.Student

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72.dao
 * @Author: zhr
 * @Date: 2022/4/10 21:50
 * @Version: V1.0
 */
@Dao    // Datebase access object 数据库操作对象，主要是用来操作数据库的，对数据库表进行增删改查操作
interface StudentDao {

    @Insert    // 增加
    fun insertStudents(vararg student: Student)

    @Update    // 更新
    fun updateStudents(vararg student: Student)

    @Query("DELETE FROM Student")    // 删除所有    删除单个 Delete
    fun deleteAllStudents()

    // 通过id进行倒序排列
    @Query("SELECT * FROM Student ORDER BY ID DESC")    // 查询所有   查询单个
    fun queryAllStudents(): List<Student>

    // 有条件的删除
    @Delete
    fun deleteStudents(vararg student: Student)

    // 有条件的获取数据
//    @Query
//    fun queryStudents(vararg student: Student)
}