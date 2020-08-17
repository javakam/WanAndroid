package com.ando.wo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

/**
 * Title: ReaderFragment
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/17  15:04
 */
class ReaderFragment : Fragment() {

    private lateinit var webView: WebView
    private val args: ReaderFragmentArgs by navArgs<ReaderFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FrameLayout(requireActivity())
        view.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        webView = WebView(requireActivity())
        val settings = webView.settings
        settings.javaScriptEnabled = true
        view.addView(webView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.loadUrl(args.link)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        webView.clearHistory()
        webView.removeAllViews()
        webView.destroy()
    }

}