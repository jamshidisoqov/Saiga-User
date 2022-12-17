package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.MainScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import uz.gita.saiga_user.presentation.main.MainScreenDirections
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : MainScreenDirection {
    override suspend fun navigateToDirectionalTaxi() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToDirectionalTaxiScreen())
    }

    override suspend fun navigateToTripsHistory() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToHistoryScreen())
    }

    override suspend fun navigateToFavouriteAddress() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToFavouriteAddressScreen())
    }

    override suspend fun navigateToCustomerCare() {
        //Todo customer care navigator.navigateTo(MainScreenDirections.)
    }

    override suspend fun navigateToSettings() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToSettingsScreen())
    }

    override suspend fun navigateToProfile() {
        navigator.navigateTo(MainScreenDirections.actionMainScreenToProfileScreen())
    }
}