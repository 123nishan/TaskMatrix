package com.nishan.taskmatrix.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.nishan.taskmatrix.data.dao.TaskDao
import com.nishan.taskmatrix.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class], version = 1,
    exportSchema = true,
//    autoMigrations = [
//        AutoMigration(
//            from = 1, to = 2, spec = TaskDatabase.TaskAutoMigration::class
//        )
//    ]
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

//    @RenameColumn(tableName = "TaskEntity", fromColumnName = "time", toColumnName = "startTime")
//    class TaskAutoMigration : AutoMigrationSpec
}