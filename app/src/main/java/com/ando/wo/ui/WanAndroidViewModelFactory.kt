package com.ando.wo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ando.wo.db.WanAndroidRepository

/**
 * Title: WanAndroidViewModelFactory
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/14  10:27
 */
class WanAndroidViewModelFactory (private val repository: WanAndroidRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WanAndroidViewModel(repository) as T
    }
}