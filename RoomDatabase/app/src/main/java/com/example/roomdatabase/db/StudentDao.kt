package com.example.roomdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)
    @Update
    suspend fun updateStudent(student: Student)
    @Delete
    suspend fun deleteStudent(student: Student)
    @Query(value= "select * from student_table")
    fun getAllStudents(): LiveData<List<Student>>
}