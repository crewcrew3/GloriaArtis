package ru.itis.gloriaartis.data.impl.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.gloriaartis.data.impl.local.storage.BasicUserDataStorageImpl
import ru.itis.gloriaartis.data.impl.repository.ArtRepositoryImpl
import ru.itis.gloriaartis.data.impl.repository.AuthRepositoryImpl
import ru.itis.gloriaartis.data.impl.repository.UserRepositoryImpl
import ru.itis.gloriaartis.domain.local.storage.BasicUserDataStorage
import ru.itis.gloriaartis.domain.repository.ArtRepository
import ru.itis.gloriaartis.domain.repository.AuthRepository
import ru.itis.gloriaartis.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface DataBinderModule {

    @Binds
    @Reusable
    fun bindUserRepositoryToImplementation(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Reusable
    fun bindAuthRepositoryToImplementation(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Reusable
    fun bindArtRepositoryToImplementation(impl: ArtRepositoryImpl): ArtRepository

    @Binds
    @Reusable
    fun bindBasicUserDataStorageToImplementation(impl: BasicUserDataStorageImpl): BasicUserDataStorage
}