package com.ando.wo.http

import com.ando.wo.bean.BaseResponse
import com.ando.wo.bean.WxArticleTabsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/13  16:05
 */
interface WanAndroidApiService {

    //https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    fun getWxArticleTabs(): Call<BaseResponse<List<WxArticleTabsEntity>>>


//    fun getWxArticleTabs(@Query("cityid") wanandroidId: String): Call<Hewanandroid>

}