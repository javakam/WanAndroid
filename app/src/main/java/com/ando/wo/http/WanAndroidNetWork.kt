package com.ando.wo.http

import com.ando.wo.bean.BaseResponse
import com.ando.wo.bean.WxArticleTabsEntity
import retrofit2.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Title: WanAndroidNetWork
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/13  16:47
 */
class WanAndroidNetWork {

    private val wanAndroidService = ServiceCreator.create(WanAndroidApiService::class.java)

    suspend fun getWxArticleTabs(): BaseResponse<List<WxArticleTabsEntity>> =
        wanAndroidService.getWxArticleTabs().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }
            })
        }
    }

    companion object {

        private var network: WanAndroidNetWork? = null

        fun getInstance(): WanAndroidNetWork {
            if (network == null) {
                synchronized(WanAndroidNetWork::class.java) {
                    if (network == null) {
                        network = WanAndroidNetWork()
                    }
                }
            }
            return network!!
        }

    }
}