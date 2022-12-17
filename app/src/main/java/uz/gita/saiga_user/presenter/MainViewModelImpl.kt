package uz.gita.saiga_user.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.saiga_user.data.remote.response.enums.CarType
import uz.gita.saiga_user.directions.MainScreenDirection
import uz.gita.saiga_user.domain.repository.MapRepository
import uz.gita.saiga_user.domain.repository.OrderRepository
import uz.gita.saiga_user.presentation.main.MainViewModel
import uz.gita.saiga_user.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val orderRepository: OrderRepository,
    private val mapRepository: MapRepository,
    private val direction: MainScreenDirection
) : MainViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val address = MutableSharedFlow<String>()

    override val openOrderDialogFlow = MutableSharedFlow<Unit>()

    override val currentLocationFlow = MutableLiveData<LatLng>()

    override fun getAddressByLocation(latLng: LatLng) {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            mapRepository.getAddressByLocation(latLng)
                .collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        address.emit(it)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
        }
    }

    override fun requestCurrentLocation() {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            mapRepository.requestCurrentLocation()
                .collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        currentLocationFlow.postValue(it)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
        }
    }

    override fun openOrderDialog() {
        viewModelScope.launch {
            openOrderDialogFlow.emit(Unit)
        }
    }

    override fun navigateToDirectionalTaxi() {
        viewModelScope.launch {
            direction.navigateToDirectionalTaxi()
        }
    }

    override fun navigateToTripsHistory() {
        viewModelScope.launch {
            direction.navigateToTripsHistory()
        }
    }

    override fun navigateToFavouriteAddress() {
        viewModelScope.launch {
            direction.navigateToFavouriteAddress()
        }
    }

    override fun navigateToCustomerCare() {
        viewModelScope.launch {
            direction.navigateToCustomerCare()
        }
    }

    override fun navigateToSettings() {
        viewModelScope.launch {
            direction.navigateToSettings()
        }
    }

    override fun navigateToProfile() {
        viewModelScope.launch {
            direction.navigateToProfile()
        }
    }
}