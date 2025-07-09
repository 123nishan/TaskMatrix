package com.nishan.taskmatrix.di

import com.nishan.taskmatrix.addtask.AddTaskViewModel
import com.nishan.taskmatrix.data.TaskRepositoryImplementation
import com.nishan.taskmatrix.domain.repository.TaskRepository
import com.nishan.taskmatrix.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::HomeViewModel)
    single<TaskRepository> { TaskRepositoryImplementation(get()) }
}