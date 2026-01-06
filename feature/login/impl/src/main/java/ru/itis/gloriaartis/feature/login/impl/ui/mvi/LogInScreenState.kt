package ru.itis.gloriaartis.feature.login.impl.ui.mvi

internal sealed interface LogInScreenState {
    data object Initial : LogInScreenState
}