package com.ando.wo.ui

import com.ando.wo.bean.WxArticleTabsEntity

class WxArticleTabsItemViewModel(tab: WxArticleTabsEntity) {

    val tabId = tab.id
    val tabName = tab.name

}