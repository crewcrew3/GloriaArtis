package ru.itis.gloriaartis.feature.login.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import ru.itis.gloriaartis.feature.login.impl.navigation.logInEntryBuilder

@Module
@InstallIn(ActivityRetainedComponent::class)
object LogInNavModule {

    @IntoSet
    @Provides
    fun provideLogInEntry(): EntryProviderScope<NavKey>.() -> Unit = {
        logInEntryBuilder()
    }
}
