package com.ando.wo.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ando.wo.base.IRepository
import com.ando.wo.bean.Article
import com.ando.wo.bean.WxArticleTabsEntity
import com.ando.wo.http.WanAndroidNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WanAndroidRepository private constructor(
    private val wanAndroidDao: WanAndroidDao,
    private val network: WanAndroidNetWork
) : IRepository {

    private suspend fun requestAllArticleTabs() = withContext(Dispatchers.IO) {
        network.getWxArticleTabs()?.data
    }

    private suspend fun requestArticleDetail(chapterId: String, pageNumber: Int): List<Article>? =
        withContext(Dispatchers.IO) {
            network.getWxArticleDetail(chapterId, pageNumber)?.data?.datas
        }

    suspend fun getAllArticleTabs(): List<WxArticleTabsEntity>? {
        var tabs: List<WxArticleTabsEntity>? = wanAndroidDao.getAll()
        if (tabs.isNullOrEmpty()) tabs = requestAllArticleTabs()
        return tabs
    }

    suspend fun getArticleDetail(chapterId: String, pageNumber: Int): List<Article>? =
        requestArticleDetail(chapterId, pageNumber)

    companion object {

        private lateinit var instance: WanAndroidRepository

        fun getInstance(
            wanAndroidDao: WanAndroidDao,
            network: WanAndroidNetWork
        ): WanAndroidRepository {
            if (!Companion::instance.isInitialized) {
                synchronized(WanAndroidRepository::class.java) {
                    if (!Companion::instance.isInitialized) {
                        instance =
                            WanAndroidRepository(
                                wanAndroidDao,
                                network
                            )
                    }
                }
            }
            return instance
        }
    }

}