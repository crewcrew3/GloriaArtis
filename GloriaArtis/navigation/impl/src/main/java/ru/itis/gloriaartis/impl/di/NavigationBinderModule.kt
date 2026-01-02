package ru.itis.gloriaartis.impl.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.gloriaartis.api.ArtNavigator
import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.impl.ArtNavigatorImpl
import ru.itis.gloriaartis.impl.ProfileNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationBinderModule {

    @Binds
    @Reusable
    fun bindArtNavigatorToImplementation(impl: ArtNavigatorImpl): ArtNavigator

    @Binds
    @Reusable
    fun bindProfileNavigatorToImplementation(impl: ProfileNavigatorImpl): ProfileNavigator
}