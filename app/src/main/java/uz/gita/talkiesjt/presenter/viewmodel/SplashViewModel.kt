package uz.gita.talkiesjt.presenter.viewmodel

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openNextScreenLiveData: LiveData<Unit>
}