package com.ando.wo.ui.wechat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ando.wo.R
import com.ando.wo.bean.Article
import com.ando.wo.databinding.FragmentWxArticleDetailsBinding
import com.ando.wo.ui.WanAndroidViewModel
import com.ando.wo.utils.InjectorUtil
import kotlinx.coroutines.Job


/**
 * Title: WxArticleDetailFragment
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/17  10:24
 */
class WxArticleDetailFragment : Fragment() {

    private val viewModel: WanAndroidViewModel by viewModels {
        InjectorUtil.getWanAndroidViewModelFactory()
    }

    private lateinit var binding: FragmentWxArticleDetailsBinding
    private val args: WxArticleDetailFragmentArgs by navArgs<WxArticleDetailFragmentArgs>()
    private var jobWxArticleDetail: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWxArticleDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val adapter = WxArticleDetailsAdapter()
        binding.rvArticleList.adapter = adapter

        initRecyclerViewRefresh()
        initRecyclerViewLoadMore()

        requestArticleList(1)
        subscribeUi(adapter)
        return binding.root
    }

    var mPageNumber: Int = 1
    var mLastVisibleItemPosition: Int = 0

    private fun initRecyclerViewRefresh() {
        binding.swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            R.color.colorAccent
        )
        if (binding.swipeRefresh.isRefreshing) return
        binding.swipeRefresh.setOnRefreshListener {
            mPageNumber = 1
            requestArticleList(mPageNumber)
        }
    }

    private fun initRecyclerViewLoadMore() {

        val onScrollListener: RecyclerView.OnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager) {
                        mLastVisibleItemPosition =
                            layoutManager.findLastVisibleItemPosition()
                    }
                    binding.rvArticleList.adapter?.let {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && mLastVisibleItemPosition + 1 == it.itemCount
                        ) {
                            mPageNumber++
                            requestArticleList(mPageNumber)
                        }
                    }
                }
            }

        binding.rvArticleList.addOnScrollListener(onScrollListener)
    }

    private fun requestArticleList(pageNumber: Int = 1) {
        jobWxArticleDetail?.cancel()
        jobWxArticleDetail = viewModel.getWxArticleDetail(args.tabId, pageNumber)
    }

    private fun subscribeUi(adapter: WxArticleDetailsAdapter) {

        viewModel.wxArticleDetails.observe(viewLifecycleOwner, Observer {
            binding.hasArticles = !it.isNullOrEmpty()

            Toast.makeText(requireActivity(), "第 $mPageNumber 页数据加载完成", Toast.LENGTH_SHORT).show()

            if (mPageNumber != 1) {
                val data = mutableListOf<Article>()
                data.addAll(adapter.currentList)
                data.addAll(it)
                //adapter.currentList.addAll(it)
                //Log.w("123", "LoadMore -> ${adapter.currentList.size}  ${it.size}  ${data.size}")

                adapter.submitList(data)
            } else {
                if (binding.swipeRefresh.isRefreshing) binding.swipeRefresh.isRefreshing = false
                adapter.submitList(it)

            }
        })


    }

}