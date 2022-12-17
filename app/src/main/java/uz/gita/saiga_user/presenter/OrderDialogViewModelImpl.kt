package uz.gita.saiga_user.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.saiga_user.data.remote.response.enums.CarType
import uz.gita.saiga_user.domain.repository.OrderRepository
import uz.gita.saiga_user.presentation.main.order.OrderDialogViewModel
import uz.gita.saiga_user.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class OrderDialogViewModelImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : OrderDialogViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val selectedCarType = MutableStateFlow(0)

    override val isFavouriteOrder = MutableStateFlow(false)

    override val dismissDialog = MutableSharedFlow<Boolean>()

    override fun setFavouriteOrder(isFavourite: Boolean) {
        viewModelScope.launch {
            isFavouriteOrder.emit(isFavourite)
        }
    }

    override fun setSelectedCarType(type: Int) {
        viewModelScope.launch {
            selectedCarType.emit(type)
        }
    }

    override fun addFavourite(whereFrom: String, whereFromLatLng: LatLng, whereTo: String) {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            orderRepository.addFavourite(whereFrom, whereFromLatLng, whereTo)
                .collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        isFavouriteOrder.emit(it)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
        }
    }

    override fun order(
        whereFrom: String,
        whereFromLatLng: LatLng,
        whereTo: String,
        carType: CarType
    ) {
        viewModelScope.launch {
            loadingSharedFlow.emit(true)
            orderRepository.order(whereFrom, whereFromLatLng, whereTo, carType)
                .collectLatest { result ->
                    loadingSharedFlow.emit(false)
                    result.onSuccess {
                        dismissDialog.emit(true)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
        }
    }


}