package uz.gita.saiga_user.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.saiga_user.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/14/2022
@Singleton
class MySharedPref @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPreferences: SharedPreferences
) : SharedPreference(context, sharedPreferences) {

    var verifyToken: String by Strings("")

    var token: String by Strings("")

    var firstName: String by Strings("")

    var lastName: String by Strings("")

    var phoneNumber: String by Strings("")

    var language:Int by Ints(1)

}