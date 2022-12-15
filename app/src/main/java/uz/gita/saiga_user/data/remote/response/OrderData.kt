package uz.gita.saiga_user.data.remote.response

// Created by Jamshid Isoqov on 12/15/2022
data class OrderData(
    val id: Long,
    val direction: DirectionsData,
    val price: Double,
    val driver: CabinetData,
    val orderedUser: UserData
)
