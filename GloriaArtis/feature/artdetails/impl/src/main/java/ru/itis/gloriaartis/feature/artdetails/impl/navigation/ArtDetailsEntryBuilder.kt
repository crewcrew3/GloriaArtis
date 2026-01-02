package ru.itis.gloriaartis.feature.artdetails.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.gloriaartis.feature.artdetails.api.ArtDetailsNavKey
import ru.itis.gloriaartis.feature.artdetails.impl.ui.ArtDetailsScreen

fun EntryProviderScope<NavKey>.artDetailsEntryBuilder() {
    entry<ArtDetailsNavKey> { key ->
        ArtDetailsScreen(
            artId = key.artId,
        )
    }
}
