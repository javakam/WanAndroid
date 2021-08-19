package com.ando.wo.ui.wechat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ando.wo.R
import com.ando.wo.databinding.FragmentWxArticleTabsBinding
import com.ando.wo.ui.WanAndroidViewModel
import com.ando.wo.utils.InjectorUtil
import kotlinx.coroutines.Job

/**
 * @author javakam
 * @date 2020/8/14  11:13
 */
class WxArticleTabsFragment : Fragment() {

    private lateinit var binding: FragmentWxArticleTabsBinding

    //ViewModelProvider(this,InjectorUtil.getWanAndroidViewModelFactory()).get(WanAndroidViewModel::class.java)
    private val viewModel: WanAndroidViewModel by viewModels {
        InjectorUtil.getWanAndroidViewModelFactory()
    }

    private var jobWxArticleTabs: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWxArticleTabsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.resId = R.color.colorPrimary
        binding.lifecycleOwner = this

        val adapter = WxArticleTabsAdapter()
        binding.rvArticleTabs.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: WxArticleTabsAdapter) {
        jobWxArticleTabs?.cancel()
        jobWxArticleTabs = viewModel.getWxArticleTabs()

        //xml databinding
        viewModel.wxArticleTabs.observe(viewLifecycleOwner, { tabs ->
            binding.hasTabs = !tabs.isNullOrEmpty()
            adapter.submitList(tabs)
        })

        //lifecycleScope.launch { }
    }

}