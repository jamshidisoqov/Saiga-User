package uz.gita.saiga_user.domain.repository

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import uz.gita.saiga_user.data.remote.response.OrderData
import uz.gita.saiga_user.data.remote.response.enums.CarType
import uz.gita.saiga_user.utils.ResultData

// Created by Jamshid Isoqov on 12/14/2022
interface OrderRepository {

    fun order(
        whereFrom: String,
        whereFromLatLng: LatLng,
        whereTo: String,
        carType: CarType
    ):Flow<ResultData<OrderData>>

    fun addFavourite(whereFrom: String, whereFromLatLng: LatLng, whereTo: String):Flow<ResultData<Boolean>>

}