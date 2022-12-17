package uz.gita.saiga_user.utils

import uz.gita.saiga_user.data.local.MySharedPref
import javax.inject.Inject

// Created by Jamshid Isoqov on 12/15/2022


const val DEBOUNCE_TEXT_CHANGES: Long = 200L
const val DEBOUNCE_VIEW_CLICK: Long = 100L

class Settings @Inject constructor(private val mySharedPref: MySharedPref) {

}