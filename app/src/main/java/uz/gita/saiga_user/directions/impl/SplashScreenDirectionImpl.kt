package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.SplashScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import uz.gita.saiga_user.presentation.splash.SplashScreenDirections
import javax.inject.Inject

class SplashScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
    }
}