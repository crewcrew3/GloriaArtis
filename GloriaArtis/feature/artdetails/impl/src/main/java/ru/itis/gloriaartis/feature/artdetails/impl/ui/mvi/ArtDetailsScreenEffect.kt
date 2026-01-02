package ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi

import androidx.annotation.StringRes

internal sealed interface ArtDetailsScreenEffect {
    data class Message(@StringRes val message: Int) : ArtDetailsScreenEffect
}