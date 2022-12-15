package uz.gita.saiga_user.data.remote.response

import uz.gita.saiga_user.data.remote.response.enums.Lang
import uz.gita.saiga_user.data.remote.response.enums.Role
import uz.gita.saiga_user.data.remote.response.enums.Status
import java.sql.Timestamp

data class UserData(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val lang: Lang = Lang.KAA,
    val username: String,
    val role: Role,
    val status: Status = Status.ACTIVE,
    val createdDate: Timestamp,
    val updatedDate: Timestamp
)