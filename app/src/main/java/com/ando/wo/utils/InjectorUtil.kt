package com.ando.wo.utils

import android.app.Application
import com.ando.wo.WanAndroidApplication
import com.ando.wo.db.AppDatabase
import com.ando.wo.db.WanAndroidRepository
import com.ando.wo.http.WanAndroidNetWork
import com.ando.wo.ui.WanAndroidViewModelFactory


object InjectorUtil {

    private val wanAndroidDao by lazy {
        AppDatabase.getDatabase(WanAndroidApplication.instance, null).wanAndroidDao()
    }

    private fun getWanAndroidRepository() =
        WanAndroidRepository.getInstance(wanAndroidDao, WanAndroidNetWork.getInstance())

    fun getWanAndroidViewModelFactory() = WanAndroidViewModelFactory(getWanAndroidRepository())

}