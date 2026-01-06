package ru.itis.gloriaartis.impl.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.gloriaartis.api.ArtNavigator
import ru.itis.gloriaartis.api.BottomBarNavigator
import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.impl.navigators.ArtNavigatorImpl
import ru.itis.gloriaartis.impl.navigators.BottomBarNavigatorImpl
import ru.itis.gloriaartis.impl.navigators.ProfileNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationBinderModule {

    @Binds
    @Reusable
    fun bindArtNavigatorToImplementation(impl: ArtNavigatorImpl): ArtNavigator

    @Binds
    @Reusable
    fun bindProfileNavigatorToImplementation(impl: ProfileNavigatorImpl): ProfileNavigator

    @Binds
    @Reusable
    fun bindBottomBarNavigatorToImplementation(impl: BottomBarNavigatorImpl): BottomBarNavigator
}