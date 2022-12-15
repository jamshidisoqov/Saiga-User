package uz.gita.saiga_user.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.saiga_user.data.local.MySharedPref
import uz.gita.saiga_user.directions.LoginScreenDirection
import uz.gita.saiga_user.domain.repository.AuthRepository
import uz.gita.saiga_user.presentation.login.LoginViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val direction: LoginScreenDirection,
    private val mySharedPref: MySharedPref
) : LoginViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override fun login(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(phone)
                .collectLatest { result ->
                    result.onSuccess {
                        mySharedPref.verifyToken = it
                        mySharedPref.phoneNumber = it
                    }.onMessage {

                    }.onError {

                    }
                }
        }
    }
}