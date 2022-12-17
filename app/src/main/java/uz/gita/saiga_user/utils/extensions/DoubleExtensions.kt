package uz.gita.saiga_user.utils.extensions

import javax.inject.Inject

// Created by Jamshid Isoqov on 12/17/2022
@Inject
fun Double.getFinanceType(): String {
    return "$this".combine("sum")
}