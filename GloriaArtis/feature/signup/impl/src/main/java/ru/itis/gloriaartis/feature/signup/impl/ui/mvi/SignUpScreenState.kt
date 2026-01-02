package ru.itis.gloriaartis.feature.signup.impl.ui.mvi

internal sealed interface SignUpScreenState {
    data object Initial : SignUpScreenState
}