package com.example.basecontent.p79top88

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72
 * @Author: zhr
 * @Date: 2022/4/10 17:13
 * @Version: V1.0
 */
class MyDbOpenHelper : SQLiteOpenHelper {

    // 1.私有构造方法
    private constructor(
        context: Context,
        name: String,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : super(context, name, factory, version)

    // 2.对外提供创建对象的方法
    companion object {
        private var mInstance: MyDbOpenHelper? = null

        @Synchronized
        public fun getInstace(context: Context): MyDbOpenHelper? {
            if (null == mInstance) {
                // 或者 zhr.sqlite
                mInstance = MyDbOpenHelper(context, "zhr.db", null, 1)
            }
            return mInstance
        }
    }

    // 创建表 表数据库初始化   数据库第一次创建时调用  意味着此函数只会初始化一次
    override fun onCreate(db: SQLiteDatabase?) {
        // 主键：primary key 必须唯一的
        // id integer类型   name text类型
        // SqliteOpenHelper 必须要通过sql语句进行操作，不能通过函数进行操作
        val sql = "create table persons(_id integer primary key autoincrement, name text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}