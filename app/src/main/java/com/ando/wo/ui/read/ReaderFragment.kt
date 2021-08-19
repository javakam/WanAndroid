package com.ando.wo.ui.read

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.ando.wo.databinding.FragmentArticleReaderBinding
import com.ando.wo.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_article_reader.*
import android.webkit.WebSettings

/**
 * @author javakam
 * @date 2020/8/17  15:04
 */
class ReaderFragment : Fragment() {

    private lateinit var binding: FragmentArticleReaderBinding
    private lateinit var webView: WebView
    private val args: ReaderFragmentArgs by navArgs()

    private val imageUrl = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleReaderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        webView = WebView(requireActivity())
        initWebView()

        binding.flReader.addView(webView)

        var isToolbarShown = false
        binding.scrollReader.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldShowToolbar = (scrollY > toolbar.height)

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    appbar.isActivated = shouldShowToolbar

                    // Show the plant name if toolbar is shown
                    binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        //防止 WebView 跳转到系统浏览器
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(
                    request?.url?.toString() ?: return super.shouldOverrideUrlLoading(view, request)
                )
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url ?: return super.shouldOverrideUrlLoading(view, url))
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        view?.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })
        webView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        settings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.displayZoomControls = false //隐藏原生的缩放控件
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        settings.allowFileAccess = true
        settings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        settings.loadsImagesAutomatically = true //支持自动加载图片
        settings.defaultTextEncodingName = "utf-8" //设置编码格式
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //toolbar.title = args.title
        binding.articleTitle = args.title

        imageUrl.value =
            "https://img2.pconline.com.cn/pconline/0704/27/1007612_070427skyflower19.jpg"
        binding.imageUrl = imageUrl.value

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        webView.loadUrl(args.link)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).handleActionBar(false)
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).handleActionBar(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView.clearHistory()
        webView.removeAllViews()
        webView.destroy()
    }

}