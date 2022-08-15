package uz.gita.talkiesjt.presenter.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.talkiesjt.presenter.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(): ViewModel(), SplashViewModel {
    override val openNextScreenLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(2000)
            openNextScreenLiveData.postValue(Unit)
        }
    }
}