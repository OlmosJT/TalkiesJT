package uz.gita.talkiesjt.presenter.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.talkiesjt.R
import uz.gita.talkiesjt.databinding.ScreenWebViewBinding

@AndroidEntryPoint
class WebViewArticleScreen: Fragment(R.layout.screen_web_view) {
    private val binding by viewBinding(ScreenWebViewBinding::bind)
    private val args: WebViewArticleScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.loadUrl(args.data.url)
    }
}