package com.ando.wo.ui.wechat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
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

        subscribeUi(adapter, args.tabId)
        return binding.root
    }

    private fun subscribeUi(adapter: WxArticleDetailsAdapter, tabId: String) {
        jobWxArticleDetail?.cancel()
        jobWxArticleDetail = viewModel.getWxArticleDetail(tabId, 1)

        viewModel.wxArticleDetails.observe(viewLifecycleOwner, Observer {
            binding.hasArticles = !it.isNullOrEmpty()
            adapter.submitList(it)
        })


    }

}