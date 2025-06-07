package com.nishan.taskmatrix.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nishan.taskmatrix.data.model.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    suspend fun getById(id: Int): TaskEntity

    @Insert
    suspend fun insert(vararg task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}