package uz.gita.saiga_user.presentation.verify

import uz.gita.saiga_user.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/15/2022
interface VerifyViewModel : BaseViewModel {

    fun resendCode()

    fun verifyCode(code: String)

}