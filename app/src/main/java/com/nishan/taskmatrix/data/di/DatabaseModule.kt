package com.nishan.taskmatrix.data.di

import android.app.Application
import androidx.room.Room
import com.nishan.taskmatrix.data.TaskDatabase
import com.nishan.taskmatrix.data.dao.TaskDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<TaskDatabase> {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            "task_dba"
        ).build()
    }
    single<TaskDao> {
        get<TaskDatabase>().taskDao()
    }
}