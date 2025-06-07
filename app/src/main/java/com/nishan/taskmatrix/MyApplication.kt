package com.nishan.taskmatrix

import android.app.Application
import com.nishan.taskmatrix.di.appModule
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}