package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.LoginScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import uz.gita.saiga_user.presentation.login.LoginScreenDirections
import javax.inject.Inject

class LoginScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : LoginScreenDirection {
    override suspend fun navigateToVerifyScreen(phone: String) {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToVerifyCodeScreen(phone))
    }

    override suspend fun navigateToRegisterScreen() {
        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToRegisterScreen())
    }
}