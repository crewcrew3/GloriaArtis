package ru.itis.gloriaartis.feature.artlist.impl.ui.mvi

import androidx.annotation.StringRes

internal sealed interface ArtListScreenEffect {
    data class Message(@StringRes val message: Int) : ArtListScreenEffect
}