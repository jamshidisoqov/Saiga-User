package uz.gita.saiga_user.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.saiga_user.data.remote.response.DirectionalTaxiData
import uz.gita.saiga_user.directions.DirectionalTaxiDirection
import uz.gita.saiga_user.domain.repository.DirectionsRepository
import uz.gita.saiga_user.presentation.directional_taxi.DirectionalTaxiViewModel
import uz.gita.saiga_user.utils.extensions.getMessage
import javax.inject.Inject

@HiltViewModel
class DirectionalTaxiViewModelImpl @Inject constructor(
    private val direction: DirectionalTaxiDirection,
    private val repository: DirectionsRepository
) : DirectionalTaxiViewModel, ViewModel() {

    override val loadingSharedFlow = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val allDirectionalTaxis = MutableStateFlow<List<DirectionalTaxiData>>(emptyList())

    private var allDirection: List<DirectionalTaxiData> = emptyList()

    override fun navigateToDirectionalDetail(directionalTaxiData: DirectionalTaxiData) {
        viewModelScope.launch {
            direction.navigateToDetail(directionalTaxiData)
        }
    }

    override fun getAllDirectionalData() {
        viewModelScope.launch {
            repository.getAllDirectionalTaxis()
                .collectLatest { result ->
                    result.onSuccess {
                        allDirection = it
                        allDirectionalTaxis.emit(it)
                    }.onMessage {
                        messageSharedFlow.emit(it)
                    }.onError {
                        errorSharedFlow.emit(it.getMessage())
                    }
                }
        }
    }

    override fun search(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val list = allDirection.filter {
                it.directionsData.addressFrom.title.startsWith(query) ||
                        it.directionsData.addressTo.title.startsWith(query)
            }
            allDirectionalTaxis.emit(list)
        }
    }


}