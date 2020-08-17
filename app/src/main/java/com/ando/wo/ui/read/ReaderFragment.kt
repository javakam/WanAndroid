package com.ando.wo.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.ando.wo.databinding.FragmentArticleReaderBinding
import kotlinx.android.synthetic.main.fragment_article_reader.*

/**
 * Title: ReaderFragment
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/17  15:04
 */
class ReaderFragment : Fragment() {

    private lateinit var binding: FragmentArticleReaderBinding
    private lateinit var webView: WebView
    private val args: ReaderFragmentArgs by navArgs<ReaderFragmentArgs>()

    private val imageUrl = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleReaderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        webView = WebView(requireActivity())
        val settings = webView.settings
        settings.javaScriptEnabled = true
        binding.flReader.addView(webView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //toolbar.title = args.title
        binding.articleTitle = args.title

        imageUrl.value = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3922290090,3177876335&fm=26&gp=0.jpg"
        binding.imageUrl = imageUrl.value

        toolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }

        webView.loadUrl(args.link)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView.clearHistory()
        webView.removeAllViews()
        webView.destroy()
    }

}