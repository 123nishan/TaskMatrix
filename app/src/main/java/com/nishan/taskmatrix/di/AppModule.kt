package com.nishan.taskmatrix.di

import com.nishan.taskmatrix.addtask.AddTaskViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AddTaskViewModel)
}