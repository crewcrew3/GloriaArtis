package ru.itis.gloriaartis.feature.login.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.itis.gloriaartis.feature.login.api.LoginNavKey
import ru.itis.gloriaartis.feature.login.impl.ui.LogInScreen

fun EntryProviderScope<NavKey>.logInEntryBuilder() {
    entry<LoginNavKey> {
        LogInScreen()
    }
}