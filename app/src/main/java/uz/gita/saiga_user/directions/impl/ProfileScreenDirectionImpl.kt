package uz.gita.saiga_user.directions.impl

import uz.gita.saiga_user.directions.ProfileScreenDirection
import uz.gita.saiga_user.navigation.Navigator
import javax.inject.Inject

class ProfileScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : ProfileScreenDirection {
    override suspend fun navigateToTrips() {
        //navigator.navigateTo()
    }

    override suspend fun navigateToSettings() {

    }

    override suspend fun navigateToServices() {

    }

    override suspend fun navigateToProfileDetail() {

    }
}