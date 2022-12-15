package uz.gita.saiga_user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.saiga_user.directions.LoginScreenDirection
import uz.gita.saiga_user.directions.RegisterScreenDirection
import uz.gita.saiga_user.directions.SplashScreenDirection
import uz.gita.saiga_user.directions.impl.LoginScreenDirectionImpl
import uz.gita.saiga_user.directions.impl.RegisterScreenDirectionImpl
import uz.gita.saiga_user.directions.impl.SplashScreenDirectionImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/14/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @[Binds Singleton]
    fun bindSplashDirections(impl: SplashScreenDirectionImpl): SplashScreenDirection

    @[Binds Singleton]
    fun bindLoginDirections(impl: LoginScreenDirectionImpl): LoginScreenDirection

    @[Binds Singleton]
    fun bindRegisterScreenDirection(impl: RegisterScreenDirectionImpl): RegisterScreenDirection

}