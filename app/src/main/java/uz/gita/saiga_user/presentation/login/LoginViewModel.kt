package uz.gita.saiga_user.presentation.login

import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/14/2022
interface LoginViewModel : BaseViewModel {

    fun login(phone: String)

}