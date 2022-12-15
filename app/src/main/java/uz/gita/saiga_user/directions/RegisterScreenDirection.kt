package uz.gita.saiga_user.directions

// Created by Jamshid Isoqov on 12/15/2022
interface RegisterScreenDirection {

    suspend fun navigateToVerify(phone: String)

    suspend fun navigateToLogin()

}