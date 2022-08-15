package uz.gita.talkiesjt.presenter.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.talkiesjt.R
import uz.gita.talkiesjt.databinding.ScreenSplashBinding
import uz.gita.talkiesjt.presenter.viewmodel.SplashViewModel
import uz.gita.talkiesjt.presenter.viewmodel.impl.SplashViewModelImpl

@AndroidEntryPoint
class SplashScreen: Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Observer
        viewModel.openNextScreenLiveData.observe(viewLifecycleOwner, Observer<Unit>{
            findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
        })
    }
}