package uz.gita.saiga_user.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.saiga_user.directions.SplashScreenDirection
import uz.gita.saiga_user.domain.enums.StartScreen
import uz.gita.saiga_user.domain.repository.AuthRepository
import uz.gita.saiga_user.presentation.splash.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val direction: SplashScreenDirection
) : SplashViewModel, ViewModel() {
    override fun navigateTo() {
        viewModelScope.launch {
            when (authRepository.getStartScreen()) {
                StartScreen.MAIN -> direction.navigateToMain()
                StartScreen.LOGIN -> direction.navigateToLogin()
            }
        }
    }
}