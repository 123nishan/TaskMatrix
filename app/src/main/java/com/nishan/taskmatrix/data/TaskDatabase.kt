package com.nishan.taskmatrix.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nishan.taskmatrix.data.dao.TaskDao
import com.nishan.taskmatrix.data.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}