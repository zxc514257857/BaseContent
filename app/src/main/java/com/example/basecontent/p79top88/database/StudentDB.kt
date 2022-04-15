package com.example.basecontent.p79top88.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.basecontent.p79top88.dao.StudentDao
import com.example.basecontent.p79top88.entity.Student

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72.database
 * @Author: zhr
 * @Date: 2022/4/10 22:02
 * @Version: V1.0
 */
// 数据库关联前面的表实体 Student     导出模式设置为false，默认为true，表示不需要这个表的json数据？？？没搞懂这是什么鬼
@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDB : RoomDatabase() {

    // 用户只需要操作dao就可以了，我们必须要暴露dao。dao被用户拿到之后，就能对数据库进行增删改查了
    public abstract fun getStudentDao(): StudentDao

    // 设置单例模式 返回DB
    companion object {
        private var INSTANCE: StudentDB? = null

        @Synchronized
        fun getInstance(context: Context): StudentDB {
            if (null == INSTANCE) {
                // 设置数据库的名字
                INSTANCE =
                    Room.databaseBuilder(
                        context.applicationContext,
                        StudentDB::class.java,
                        "sutdent.db"
                    )
                        // 默认是异步线程操作数据库
                        // 可以强制开始主线程操作数据库！ 慎用
//                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }
}