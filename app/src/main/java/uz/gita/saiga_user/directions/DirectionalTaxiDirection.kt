package uz.gita.saiga_user.directions

import uz.gita.saiga_user.data.remote.response.DirectionalTaxiData

// Created by Jamshid Isoqov on 12/17/2022
interface DirectionalTaxiDirection {

    suspend fun navigateToDetail(directionalTaxiData: DirectionalTaxiData)

}