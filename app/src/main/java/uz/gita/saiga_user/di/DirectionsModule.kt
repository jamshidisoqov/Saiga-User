package uz.gita.saiga_user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.saiga_user.directions.*
import uz.gita.saiga_user.directions.impl.*
import uz.gita.saiga_user.presenter.DirectionalTaxiViewModelImpl
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
    fun bindRegisterScreenDirections(impl: RegisterScreenDirectionImpl): RegisterScreenDirection

    @[Binds Singleton]
    fun bindVerifyDirections(impl: VerifyScreenDirectionImpl): VerifyScreenDirection

    @[Binds Singleton]
    fun bindMainDirections(impl: MainScreenDirectionImpl): MainScreenDirection

    @[Binds Singleton]
    fun bindProfileDirections(impl: ProfileScreenDirectionImpl): ProfileScreenDirection

    @[Binds Singleton]
    fun bindDirectionalTaxi(impl: DirectionalTaxiViewModelImpl): DirectionalTaxiDirection

}