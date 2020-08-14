package com.ando.wo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WanAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

}