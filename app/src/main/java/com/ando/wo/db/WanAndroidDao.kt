package com.ando.wo.db

import androidx.room.*
import com.ando.wo.bean.WxArticleTabsEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author javakam
 * @date 2020/8/13  16:58
 */
@Dao
interface WanAndroidDao {

    //Article Tabs
    //BUG: https://stackoverflow.com/questions/48694449/error-with-room-dao-class-when-using-kotlin-coroutines

    @Query("select * from t_article_tabs order by tabId asc")
    fun getAll(): Flow<List<WxArticleTabsEntity>?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(tab: WxArticleTabsEntity)

    @Query("select * from t_article_tabs where tabId in (:tabIds)")
    fun loadAllByIds(tabIds: IntArray): Flow<List<WxArticleTabsEntity>?>

    @Query("select * from t_article_tabs where tabName like :tabName limit 1")
    fun findByName(tabName: String): Flow<WxArticleTabsEntity?>

    @Query("delete from t_article_tabs")
    fun deleteAll()

    @Delete
    fun delete(tab: WxArticleTabsEntity)

}