package com.primakpavel.roomsample

import android.app.Application

class App : Application() {
    var appExecutor: AppExecutors? = null
    override fun onCreate() {
        super.onCreate()
        appExecutor = AppExecutors()
    }
}