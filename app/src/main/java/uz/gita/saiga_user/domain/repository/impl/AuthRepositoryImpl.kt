package uz.gita.saiga_user.domain.repository.impl

import uz.gita.saiga_user.data.local.MySharedPref
import uz.gita.saiga_user.domain.enums.StartScreen
import uz.gita.saiga_user.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val mySharedPref: MySharedPref
) : AuthRepository {
    override suspend fun getStartScreen(): StartScreen {
        return if (mySharedPref.token.isEmpty()) StartScreen.LOGIN
        else StartScreen.MAIN
    }
}