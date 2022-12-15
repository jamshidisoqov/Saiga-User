package uz.gita.saiga_user.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.saiga_user.data.remote.response.UserData
import uz.gita.saiga_user.domain.enums.StartScreen
import uz.gita.saiga_user.utils.ResultData

// Created by Jamshid Isoqov on 12/14/2022
interface AuthRepository {

    suspend fun getStartScreen(): StartScreen

    fun login(phoneNumber: String): Flow<ResultData<UserData>>

    fun register(
        phoneNumber: String,
        firstName: String,
        lastName: String
    ): Flow<ResultData<UserData>>

}