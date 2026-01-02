package ru.itis.gloriaartis.feature.login.impl.ui.mvi

import androidx.annotation.StringRes

internal sealed interface LogInScreenEffect {
    data class Message(@StringRes val message: Int) : LogInScreenEffect
}