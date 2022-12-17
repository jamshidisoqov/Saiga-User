package uz.gita.saiga_user.presentation.main.order

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.saiga_user.data.remote.response.enums.CarType
import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/17/2022
interface OrderDialogViewModel : BaseViewModel {

    val selectedCarType: StateFlow<Int>

    val isFavouriteOrder: StateFlow<Boolean>

    val dismissDialog: SharedFlow<Boolean>

    fun setFavouriteOrder(isFavourite:Boolean)

    fun setSelectedCarType(type:Int)

    fun addFavourite(whereFrom: String, whereFromLatLng: LatLng, whereTo: String)

    fun order(whereFrom: String, whereFromLatLng: LatLng, whereTo: String, carType: CarType)

}