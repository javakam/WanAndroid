package com.ando.wo.ui.wechat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ando.wo.R
import com.ando.wo.bean.WxArticleTabsEntity
import com.ando.wo.databinding.ListItemWxArticleTabBinding
import com.ando.wo.utils.setClipDate
import com.ando.wo.utils.toArticleListFragment

/**
 * Title: WxArticleTabsAdapter
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/14  16:25
 */
class WxArticleTabsAdapter : ListAdapter<WxArticleTabsEntity, WxArticleTabsAdapter.ViewHolder>(
    WxArticleTabsAdapterDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_wx_article_tab, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemWxArticleTabBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.viewModel?.tabId?.let { tabId ->
                    toArticleListFragment(view, tabId)
                }
            }

            binding.cardTabName.setOnLongClickListener {
                setClipDate(binding.tvTabName.text.toString())
                Toast.makeText(it.context, "已复制", Toast.LENGTH_SHORT).show()
                true
            }
        }

        fun bind(tab: WxArticleTabsEntity) {
            with(binding) {
                viewModel = WxArticleTabsItemViewModel(tab)
                executePendingBindings()
            }
        }
    }
}

private class WxArticleTabsAdapterDiffCallback : DiffUtil.ItemCallback<WxArticleTabsEntity>() {

    override fun areItemsTheSame(
        oldItem: WxArticleTabsEntity,
        newItem: WxArticleTabsEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WxArticleTabsEntity,
        newItem: WxArticleTabsEntity
    ): Boolean {
        return oldItem.name == newItem.name
    }
}

//ItemView.xml & Item
class WxArticleTabsItemViewModel(tab: WxArticleTabsEntity) {

    val tabId = tab.id
    val tabName = tab.name

}