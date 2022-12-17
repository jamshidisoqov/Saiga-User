package uz.gita.saiga_user.directions

// Created by Jamshid Isoqov on 12/16/2022
interface MainScreenDirection {

    suspend fun navigateToDirectionalTaxi()

    suspend fun navigateToTripsHistory()

    suspend fun navigateToFavouriteAddress()

    suspend fun navigateToCustomerCare()

    suspend fun navigateToSettings()

    suspend fun navigateToProfile()


}