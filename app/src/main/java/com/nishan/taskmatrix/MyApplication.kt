package com.nishan.taskmatrix

import android.app.Application
import com.nishan.taskmatrix.data.di.databaseModule
import com.nishan.taskmatrix.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, databaseModule)
        }
    }
}