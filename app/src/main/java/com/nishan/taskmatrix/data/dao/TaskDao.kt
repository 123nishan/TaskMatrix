package com.nishan.taskmatrix.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nishan.taskmatrix.data.model.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getById(id: Int): TaskEntity

    @Insert
    fun insert(vararg task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)


}