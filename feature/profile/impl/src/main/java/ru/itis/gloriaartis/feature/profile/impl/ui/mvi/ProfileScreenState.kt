package ru.itis.gloriaartis.feature.profile.impl.ui.mvi

import ru.itis.gloriaartis.domain.model.UserModel

internal sealed interface ProfileScreenState {
    data object Initial : ProfileScreenState
    data object Loading : ProfileScreenState
    data object Unauthorized : ProfileScreenState
    data class ProfileResult(val result: UserModel) : ProfileScreenState
}