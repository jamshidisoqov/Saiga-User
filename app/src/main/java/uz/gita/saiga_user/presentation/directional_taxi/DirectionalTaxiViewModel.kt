package uz.gita.saiga_user.presentation.directional_taxi

import kotlinx.coroutines.flow.StateFlow
import uz.gita.saiga_user.data.remote.response.DirectionalTaxiData
import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/17/2022
interface DirectionalTaxiViewModel : BaseViewModel {

    val allDirectionalTaxis: StateFlow<List<DirectionalTaxiData>>

    fun navigateToDirectionalDetail(directionalTaxiData: DirectionalTaxiData)

    fun getAllDirectionalData()

    fun search(query: String)

}