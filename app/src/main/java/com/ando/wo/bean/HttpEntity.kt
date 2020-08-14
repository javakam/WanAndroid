package com.ando.wo.bean

import android.graphics.Bitmap
import androidx.room.*
import com.google.gson.annotations.SerializedName

/**
 * Title: BaseResponse
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/13  16:30
 */
data class BaseResponse<T>(
    @SerializedName("data") val data: T?,
    @SerializedName("errorCode") val errorCode: Int? = 0,
    @SerializedName("errorMsg") val errorMsg: String? = ""
)

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity(tableName = "t_article_tabs")
data class WxArticleTabsEntity(
    @ColumnInfo(name = "tabId") @PrimaryKey @SerializedName("id") val id: Int?,
    @ColumnInfo(name = "tabName") @SerializedName("name") val name: String?,

    @SerializedName("order") val order: Int?,
    @SerializedName("courseId") val courseId: Int?,
    @SerializedName("parentChapterId") val parentChapterId: Int?,
    @SerializedName("userControlSetTop") val userControlSetTop: Boolean? = false,
    @SerializedName("visible") val visible: Int? = 1
) {
    @Ignore
    @SerializedName("children")
    val children: List<Any>? = null
}


// 通用的带有列表数据的实体
data class BaseListResponseBody<T>(
    @SerializedName("curPage") val curPage: Int,
    @SerializedName("datas") val datas: List<T>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("over") val over: Boolean,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("total") val total: Int
)

//文章
data class ArticleResponseBody(
    @SerializedName("curPage") val curPage: Int,
    @SerializedName("datas") var datas: MutableList<Article>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("over") val over: Boolean,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("total") val total: Int
)

//文章
data class Article(
    @SerializedName("apkLink") val apkLink: String,
    @SerializedName("audit") val audit: Int,
    @SerializedName("author") val author: String,
    @SerializedName("chapterId") val chapterId: Int,
    @SerializedName("chapterName") val chapterName: String,
    @SerializedName("collect") var collect: Boolean,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("desc") val desc: String,
    @SerializedName("envelopePic") val envelopePic: String,
    @SerializedName("fresh") val fresh: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("niceDate") val niceDate: String,
    @SerializedName("niceShareDate") val niceShareDate: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("prefix") val prefix: String,
    @SerializedName("projectLink") val projectLink: String,
    @SerializedName("publishTime") val publishTime: Long,
    @SerializedName("shareDate") val shareDate: String,
    @SerializedName("shareUser") val shareUser: String,
    @SerializedName("superChapterId") val superChapterId: Int,
    @SerializedName("superChapterName") val superChapterName: String,
    @SerializedName("tags") val tags: MutableList<Tag>,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("visible") val visible: Int,
    @SerializedName("zan") val zan: Int,
    @SerializedName("top") var top: String
)

data class Tag(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

//轮播图
data class Banner(
    @SerializedName("desc") val desc: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imagePath") val imagePath: String,
    @SerializedName("isVisible") val isVisible: Int,
    @SerializedName("order") val order: Int,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Int,
    @SerializedName("url") val url: String
)

data class HotKey(
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("visible") val visible: Int
)

//常用网站
data class Friend(
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("visible") val visible: Int
)

//知识体系
data class KnowledgeTreeBody(
    @SerializedName("children") val children: MutableList<Knowledge>,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("parentChapterId") val parentChapterId: Int,
    @SerializedName("visible") val visible: Int
)

data class Knowledge(
    @SerializedName("children") val children: List<Any>,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("parentChapterId") val parentChapterId: Int,
    @SerializedName("visible") val visible: Int
)

// 登录数据
data class LoginData(
    @SerializedName("chapterTops") val chapterTops: MutableList<String>,
    @SerializedName("collectIds") val collectIds: MutableList<String>,
    @SerializedName("email") val email: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("password") val password: String,
    @SerializedName("token") val token: String,
    @SerializedName("type") val type: Int,
    @SerializedName("username") val username: String
)

//收藏网站
data class CollectionWebsite(
    @SerializedName("desc") val desc: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("link") var link: String,
    @SerializedName("name") var name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("visible") val visible: Int
)

data class CollectionArticle(
    @SerializedName("author") val author: String,
    @SerializedName("chapterId") val chapterId: Int,
    @SerializedName("chapterName") val chapterName: String,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("desc") val desc: String,
    @SerializedName("envelopePic") val envelopePic: String,
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("niceDate") val niceDate: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("originId") val originId: Int,
    @SerializedName("publishTime") val publishTime: Long,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("visible") val visible: Int,
    @SerializedName("zan") val zan: Int
)

// 导航
data class NavigationBean(
    val articles: MutableList<Article>,
    val cid: Int,
    val name: String
)

// 项目
data class ProjectTreeBean(
    @SerializedName("children") val children: List<Any>,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("parentChapterId") val parentChapterId: Int,
    @SerializedName("visible") val visible: Int
)

// 热门搜索
data class HotSearchBean(
    @SerializedName("id") val id: Int,
    @SerializedName("link") val link: String,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("visible") val visible: Int
)

// 搜索历史
data class SearchHistoryBean(val key: String) {
    val id: Long = 0
}

// TODO工具 类型
data class TodoTypeBean(
    val type: Int,
    val name: String,
    var isSelected: Boolean
)

// TODO实体类
data class TodoBean(
    @SerializedName("id") val id: Int,
    @SerializedName("completeDate") val completeDate: String,
    @SerializedName("completeDateStr") val completeDateStr: String,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: Long,
    @SerializedName("dateStr") val dateStr: String,
    @SerializedName("status") val status: Int,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("priority") val priority: Int
)

data class TodoListBean(
    @SerializedName("date") val date: Long,
    @SerializedName("todoList") val todoList: MutableList<TodoBean>
)

// 所有TODO，包括待办和已完成
data class AllTodoResponseBody(
    @SerializedName("type") val type: Int,
    @SerializedName("doneList") val doneList: MutableList<TodoListBean>,
    @SerializedName("todoList") val todoList: MutableList<TodoListBean>
)

data class TodoResponseBody(
    @SerializedName("curPage") val curPage: Int,
    @SerializedName("datas") val datas: MutableList<TodoBean>,
    @SerializedName("offset") val offset: Int,
    @SerializedName("over") val over: Boolean,
    @SerializedName("pageCount") val pageCount: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("total") val total: Int
)

// 新增TODO的实体
data class AddTodoBean(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: String,
    @SerializedName("type") val type: Int
)

// 更新TODO的实体
data class UpdateTodoBean(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: String,
    @SerializedName("status") val status: Int,
    @SerializedName("type") val type: Int
)

// 公众号列表实体
data class WXChapterBean(
    @SerializedName("children") val children: MutableList<String>,
    @SerializedName("courseId") val courseId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("parentChapterId") val parentChapterId: Int,
    @SerializedName("userControlSetTop") val userControlSetTop: Boolean,
    @SerializedName("visible") val visible: Int
)

// 用户个人信息
data class UserInfoBody(
    @SerializedName("coinCount") val coinCount: Int, // 总积分
    @SerializedName("rank") val rank: Int, // 当前排名
    @SerializedName("userId") val userId: Int,
    @SerializedName("username") val username: String
)

// 个人积分实体
data class UserScoreBean(
    @SerializedName("coinCount") val coinCount: Int,
    @SerializedName("date") val date: Long,
    @SerializedName("desc") val desc: String,
    @SerializedName("id") val id: Int,
    @SerializedName("reason") val reason: String,
    @SerializedName("type") val type: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String
)

// 排行榜实体
data class CoinInfoBean(
    @SerializedName("coinCount") val coinCount: Int,
    @SerializedName("level") val level: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("username") val username: String
)

// 我的分享
data class ShareResponseBody(
    val coinInfo: CoinInfoBean,
    val shareArticles: ArticleResponseBody
)