package uz.gita.saiga_user.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.saiga_user.data.local.MySharedPref
import uz.gita.saiga_user.utils.Settings
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/17/2022
@Module
@InstallIn(SingletonComponent::class)
object OthersModule {

    @[Provides Singleton]
    fun provideSettings(mySharedPref: MySharedPref):Settings = Settings(mySharedPref)

}