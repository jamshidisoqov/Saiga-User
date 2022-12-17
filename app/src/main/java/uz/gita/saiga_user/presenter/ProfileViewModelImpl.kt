package uz.gita.saiga_user.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.gita.saiga_user.data.local.MySharedPref
import uz.gita.saiga_user.directions.ProfileScreenDirection
import uz.gita.saiga_user.presentation.profile.ProfileViewModel
import uz.gita.saiga_user.utils.extensions.combine
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val mySharedPref: MySharedPref,
    private val direction: ProfileScreenDirection
) : ProfileViewModel,ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val nameFlow = MutableStateFlow("")

    override val phoneFlow = MutableStateFlow("")

    override fun editProfile() {
        viewModelScope.launch {
            direction.navigateToProfileDetail()
        }
    }

    override fun openMyTrips() {
        viewModelScope.launch {
            direction.navigateToTrips()
        }
    }

    override fun openSettings() {
        viewModelScope.launch {
            direction.navigateToSettings()
        }
    }

    override fun openServices() {
        viewModelScope.launch {
            direction.navigateToServices()
        }
    }

    override fun getData() {
        viewModelScope.launch(Dispatchers.IO){
            loadingSharedFlow.emit(true)
            nameFlow.emit(mySharedPref.firstName.combine(mySharedPref.lastName))
            phoneFlow.emit(mySharedPref.phoneNumber)
            loadingSharedFlow.emit(false)
        }
    }
}