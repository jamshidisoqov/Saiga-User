package uz.gita.saiga_user.presentation.profile

import kotlinx.coroutines.flow.StateFlow
import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/17/2022
interface ProfileViewModel:BaseViewModel {

    val nameFlow: StateFlow<String>

    val phoneFlow: StateFlow<String>

    fun editProfile()

    fun openMyTrips()

    fun openSettings()

    fun openServices()

    fun getData()

}