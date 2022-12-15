package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.RegisterScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import uz.gita.saiga_user.presentation.register.RegisterScreenDirections
import javax.inject.Inject

class RegisterScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : RegisterScreenDirection {
    override suspend fun navigateToVerify(phone: String) {
        navigator.navigateTo(RegisterScreenDirections.actionRegisterScreenToVerifyCodeScreen(phone))
    }

    override suspend fun navigateToLogin() {
        navigator.navigateTo(RegisterScreenDirections.actionRegisterScreenToLoginScreen())
    }
}