package com.example.basecontent.p79top88

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.basecontent.R
import com.example.basecontent.p79top88.entity.Student
import com.example.basecontent.p79top88.room.DBEngine

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72
 * @Author: zhr
 * @Date: 2022/4/10 14:09
 * @Version: V1.0
 */

// 数据存储
// 大厂的包名会做得很长很长：主要是为了让包名不会重复
// data - data - 包名下面会存储database或者sp缓存数据等（数据存储数据）
// Sqlite数据库 原生数据库    Room数据库 第三方数据库
// 1.sp
// 2.sqlite
// 3.Room
class P72Activity : AppCompatActivity() {

    @SuppressLint("WrongConstant", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p79)

        // Windows 配置信息数据格式 ini
        // Android 配置信息数据格式 xml
        // SP 存储数据 是以Key Value形式存储的(Map)
        // SP的使用： SP模式：追加或者是覆盖

        // SP_ZHR 是指xml文件夹的名字
        // 设置值
        val sp = getSharedPreferences("SP_ZHR", Context.MODE_PRIVATE)
        sp.edit().putString("aaa", "555").apply()

        // 获取值   获取SP_ZHR这个SP文件
        val sp1 = getSharedPreferences("SP_ZHR", Context.MODE_APPEND)
        // 获取这个文件中  键名为aaa的值，默认值为“”
        sp1.getString("aaa", "")
        // 然后进行回显数据就可以了

        // Sqlite数据库的使用
        // sqlite数据库 属于嵌入式开发数据库，大小以KB进行计算
        // sqlite3数据库 支持 NULL类型、INTEGER类型、REAL（浮点数）类型、TEXT（字符串文本）类型
        // 、BLOB（二进制对象）类型，这五种数据类型。所有传入的数据类型，最终都会转换为字符串文本类型
        // 主键号必须是Integer类型的格式，_id 这种，数据库字段必须是对象类型，不能是int、long等类型
        // sqlite是通过sqlite.c执行程序来创建数据库 和mysql不一样
        // select * from 表名   查看这个表中的内容
        // SQLiteOpenHelper 数据库的打开帮助类 onCreate 和 onUpgrade。构造函数需要传入数据库名称和版本号
        // SqliteOpenHelper 可以当做工具类看待  想到工具类就想到了单例模式  单例模式的特点如下：
        // 私有构造方法 + 对外提供统一的创建对象的方法
        createDb()

        // Room数据库  Room三角色介绍 是对Sqlite数据库进行封装
        testRoom()
        val btnInsert = findViewById<Button>(R.id.btn_insert)
        val btnUpdate = findViewById<Button>(R.id.btn_update)
        val btnDelete = findViewById<Button>(R.id.btn_delete)
        val btnDeleteAll = findViewById<Button>(R.id.btn_delete_all)
        val btnQueryAll = findViewById<Button>(R.id.btn_query_all)
        // 为什么不使用单例模式进行调用呢？难道是单例 + Context 容易出现问题？
        val dbEngine = DBEngine(this)
        btnInsert.setOnClickListener {
            dbEngine.insertStudents(
                Student("zhangdan1", 21),
                Student("zhangdan2", 22),
                Student("zhangdan3", 23)
            )
        }
        // android中的主键自增id 应该是从1开始的
        btnUpdate.setOnClickListener {
            val student = Student("zhangdan4", 24)
            student.id = 2
            dbEngine.updateStudents(student)
        }
        // 一般查询和删除都是根据主键id进行的操作
        btnDelete.setOnClickListener {
            val student = Student("", 0)
            student.id = 1
            dbEngine.deleteStudents(student)
        }
        btnQueryAll.setOnClickListener {
            dbEngine.quaryAllStudents()
        }
        btnDeleteAll.setOnClickListener {
            dbEngine.deleteAllStudents()
        }
    }

    private fun createDb() {
        val myDbOpenHelper = MyDbOpenHelper.getInstace(this)
        // 调用这个之后才会生成数据库文件，但还没有创建表  数据库的可写包括可读
        val writableDatabase = myDbOpenHelper?.writableDatabase
    }

    private fun testRoom() {
        // Entity   DAO   DB
        // @Entity  class Student{}
        // @Dao  class StudentDao{}
        // @Database("数据库名字"，"数据库版本号")   class StudentDB{}

    }
}