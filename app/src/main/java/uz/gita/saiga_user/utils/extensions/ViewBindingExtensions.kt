package uz.gita.saiga_user.utils.extensions

import androidx.viewbinding.ViewBinding

// Created by Jamshid Isoqov on 12/12/2022
fun <T : ViewBinding> T.include(block: T.() -> Unit) {
    block(this)
}
