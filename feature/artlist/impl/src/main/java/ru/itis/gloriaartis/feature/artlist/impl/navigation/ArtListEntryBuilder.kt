package ru.itis.gloriaartis.feature.artlist.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.gloriaartis.feature.artlist.api.ArtListNavKey
import ru.itis.gloriaartis.feature.artlist.impl.ui.ArtListScreen

fun EntryProviderScope<NavKey>.artListEntryBuilder() {
    entry<ArtListNavKey> {
        ArtListScreen()
    }
}
