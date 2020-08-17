package com.ando.wo.http

import com.ando.wo.bean.Article
import com.ando.wo.bean.BasePage
import com.ando.wo.bean.BaseResponse
import com.ando.wo.bean.WxArticleTabsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    //eg https://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    fun getWxArticleTabs(): Call<BaseResponse<List<WxArticleTabsEntity>>?>


    //eg https://wanandroid.com/wxarticle/list/409/1/json
    @GET("wxarticle/list/{chapterId}/{pageNumber}/json")
    fun getWxArticleDetail(
        @Path(value = "chapterId") chapterId: String,
        @Path(value = "pageNumber") pageNumber: Int = 1
    ): Call<BaseResponse<BasePage<List<Article>?>?>?>


}