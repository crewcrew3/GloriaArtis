package ru.itis.gloriaartis.feature.profile.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.gloriaartis.feature.profile.api.ProfileNavKey
import ru.itis.gloriaartis.feature.profile.impl.ui.ProfileScreen

fun EntryProviderScope<NavKey>.profileEntryBuilder() {
    entry<ProfileNavKey> {
        ProfileScreen()
    }
}