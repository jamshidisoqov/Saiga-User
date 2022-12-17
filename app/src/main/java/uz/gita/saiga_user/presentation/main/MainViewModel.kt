package uz.gita.saiga_user.presentation.main

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/16/2022
interface MainViewModel : BaseViewModel {

    val address: SharedFlow<String>

    val openOrderDialogFlow: SharedFlow<Unit>

    val currentLocationFlow: LiveData<LatLng>

    fun getAddressByLocation(latLng: LatLng)

    fun requestCurrentLocation()

    fun openOrderDialog()

    fun navigateToDirectionalTaxi()

    fun navigateToTripsHistory()

    fun navigateToFavouriteAddress()

    fun navigateToCustomerCare()

    fun navigateToSettings()

    fun navigateToProfile()
}