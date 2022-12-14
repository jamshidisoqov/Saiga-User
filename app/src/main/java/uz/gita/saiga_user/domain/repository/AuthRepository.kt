package uz.gita.saiga_user.domain.repository

import uz.gita.saiga_user.domain.enums.StartScreen

// Created by Jamshid Isoqov on 12/14/2022
interface AuthRepository {

    suspend fun getStartScreen(): StartScreen

}