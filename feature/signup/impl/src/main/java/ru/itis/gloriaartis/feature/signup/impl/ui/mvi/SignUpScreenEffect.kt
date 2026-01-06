package ru.itis.gloriaartis.feature.signup.impl.ui.mvi

import androidx.annotation.StringRes

internal sealed interface SignUpScreenEffect {
    data class Message(@StringRes val message: Int) : SignUpScreenEffect
    data object ErrorNicknameInput : SignUpScreenEffect
    data object ErrorPhoneNumberInput : SignUpScreenEffect
    data object ErrorPasswordInput : SignUpScreenEffect
    data object ErrorRepeatPasswordInput : SignUpScreenEffect
}