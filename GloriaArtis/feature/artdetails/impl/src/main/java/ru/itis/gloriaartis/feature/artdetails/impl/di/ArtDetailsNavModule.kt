package ru.itis.gloriaartis.feature.artdetails.impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet
import ru.itis.gloriaartis.feature.artdetails.impl.navigation.artDetailsEntryBuilder

@Module
@InstallIn(ActivityRetainedComponent::class)
object ArtDetailsNavModule {

    @IntoSet
    @Provides
    fun provideArtDetailsEntry(): EntryProviderScope<NavKey>.() -> Unit = {
        artDetailsEntryBuilder()
    }
}
