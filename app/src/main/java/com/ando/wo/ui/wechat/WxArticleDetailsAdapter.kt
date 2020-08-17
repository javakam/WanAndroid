package com.ando.wo.ui.wechat

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ando.wo.R
import com.ando.wo.bean.Article
import com.ando.wo.databinding.ListItemWxArticleDetailBinding
import com.ando.wo.utils.setClipDate


/**
 * Title: WxArticleDetailsAdapter
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/14  16:25
 */
class WxArticleDetailsAdapter : ListAdapter<Article, WxArticleDetailsAdapter.ViewHolder>(
    WxArticleDetailsAdapterDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_wx_article_detail, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemWxArticleDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.viewModel?.articleLink?.let { link ->
                    val destination =
                        WxArticleDetailFragmentDirections.actionArticleDetailsFragmentToReadFragment(
                            binding.viewModel?.articleTitle ?: "", link
                        )
                    view.findNavController().navigate(destination)
                }
            }

            binding.cardArticleTitle.setOnLongClickListener {
                setClipDate(binding.tvArticleTitle.text.toString())
                Toast.makeText(it.context, "已复制", Toast.LENGTH_SHORT).show()
                true
            }
        }

        fun bind(article: Article) {
            with(binding) {
                viewModel = WxArticleDetailItemViewModel(article)
                executePendingBindings()
            }
        }
    }
}


private class WxArticleDetailsAdapterDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.title == newItem.title
    }
}

//ItemView.xml & Item
class WxArticleDetailItemViewModel(article: Article) {

    val articleId = article.id
    val articleTitle = article.title
    val articleLink = article.link

}