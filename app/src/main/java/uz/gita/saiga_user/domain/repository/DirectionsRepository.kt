package uz.gita.saiga_user.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.saiga_user.data.remote.response.DirectionalTaxiData
import uz.gita.saiga_user.utils.ResultData

// Created by Jamshid Isoqov on 12/14/2022
interface DirectionsRepository {

    fun getAllDirectionalTaxis(): Flow<ResultData<List<DirectionalTaxiData>>>

}