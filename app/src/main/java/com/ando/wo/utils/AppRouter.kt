package com.ando.wo.utils

import android.view.View
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.ando.wo.ui.wechat.WxArticleDetailFragmentDirections
import com.ando.wo.ui.wechat.WxArticleTabsFragmentDirections

/**
 * @author javakam
 * @date 2020/8/17  9:47
 */

fun toArticleListFragment(@NonNull view: View, tabId: Int) {
    val destination =
        WxArticleTabsFragmentDirections.actionTabsFragmentToDetailsFragment(tabId.toString())
    view.findNavController().navigate(destination)
}

fun toReaderFragment(@NonNull view: View, title: String, link: String) {
    val destination =
        WxArticleDetailFragmentDirections.actionArticleDetailsFragmentToReadFragment(
            noNull(title),
            noNull(link)
        )
    view.findNavController().navigate(destination)
}