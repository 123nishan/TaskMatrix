package com.nishan.taskmatrix.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.ProvidedAutoMigrationSpec
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nishan.taskmatrix.data.dao.TaskDao
import com.nishan.taskmatrix.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class], version = 3,
    exportSchema = true,
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

}

