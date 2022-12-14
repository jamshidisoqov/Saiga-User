package uz.gita.saiga_user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.saiga_user.domain.repository.AuthRepository
import uz.gita.saiga_user.domain.repository.impl.AuthRepositoryImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/14/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}