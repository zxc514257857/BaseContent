package com.example.basecontent.p79top88.room

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.basecontent.p79top88.dao.StudentDao
import com.example.basecontent.p79top88.database.StudentDB
import com.example.basecontent.p79top88.entity.Student

/**
 * @Des:
 *
 * @Title:
 * @Project: BaseContent
 * @Package: com.example.basecontent.p72.room
 * @Author: zhr
 * @Date: 2022/4/10 22:35
 * @Version: V1.0
 */
class DBEngine {

    private var studentDao: StudentDao? = null

    constructor(context: Context) {
        val studentDb = StudentDB.getInstance(context)
        studentDao = studentDb.getStudentDao()
    }

    // dao增删改查
    fun insertStudents(vararg student: Student) {
        InsertAsyncTask(studentDao).execute(*student)
    }

    fun updateStudents(vararg student: Student) {
        UpdateAsyncTask(studentDao).execute(*student)
    }

    fun deleteStudents(vararg student: Student) {
        DeleteAsyncTask(studentDao).execute(*student)
    }

    fun deleteAllStudents() {
        DeleteAllAsyncTask(studentDao).execute()
    }

    fun quaryAllStudents() {
        QuaryAllAsyncTask(studentDao).execute()
    }

    companion object {
        class InsertAsyncTask(private val dao: StudentDao?) : AsyncTask<Student, Unit, Unit>() {
            override fun doInBackground(vararg student: Student) {
                dao?.insertStudents(*student)
            }
        }

        class QuaryAllAsyncTask(private val dao: StudentDao?) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                val students = dao?.queryAllStudents()
                Log.e("TAG", "QuaryAllAsyncTask: ${
                    students?.joinToString {
                        it.toString()
                    }
                }")
            }
        }

        class DeleteAsyncTask(private val dao: StudentDao?) : AsyncTask<Student, Unit, Unit>() {
            override fun doInBackground(vararg params: Student?) {
                dao?.deleteStudents()
            }
        }

        class DeleteAllAsyncTask(private val dao: StudentDao?) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                dao?.deleteAllStudents()
            }
        }

        class UpdateAsyncTask(private val dao: StudentDao?) : AsyncTask<Student, Unit, Unit>() {
            override fun doInBackground(vararg student: Student) {
                dao?.updateStudents(*student)
            }
        }
    }
}