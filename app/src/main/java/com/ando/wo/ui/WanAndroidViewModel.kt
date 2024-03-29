package com.ando.wo.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ando.wo.WanAndroidApplication
import com.ando.wo.bean.Article
import com.ando.wo.bean.WxArticleTabsEntity
import com.ando.wo.db.WanAndroidRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author javakam
 * @date 2020/8/14  10:27
 */
class WanAndroidViewModel internal constructor(private val repository: WanAndroidRepository) :
    ViewModel() {

    val wxArticleTabs = MutableLiveData<List<WxArticleTabsEntity>>()
    val wxArticleDetails = MutableLiveData<List<Article>>()

    fun getWxArticleTabs(): Job =
        launch(
            {
                wxArticleTabs.value = repository.getAllArticleTabs()
            },
            {
                Toast.makeText(WanAndroidApplication.instance, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        )


    fun getWxArticleDetail(chapterId: String, pageNumber: Int): Job =
        launch(
            {
                wxArticleDetails.value = repository.getArticleDetail(chapterId, pageNumber)
            },
            {
                Toast.makeText(WanAndroidApplication.instance, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        )

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

}