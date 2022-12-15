package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.VerifyScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import uz.gita.saiga_user.presentation.verify.VerifyCodeScreenDirections
import javax.inject.Inject


class VerifyScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
): VerifyScreenDirection {
    override suspend fun navigateToMain() {
        navigator.navigateTo(VerifyCodeScreenDirections.actionVerifyCodeScreenToMainScreen())
    }
}