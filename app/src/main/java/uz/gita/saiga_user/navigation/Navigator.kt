package uz.gita.saiga_user.navigation

import androidx.navigation.NavDirections

typealias Direction = NavDirections

interface Navigator {
    suspend fun navigateTo(direction: Direction)
}