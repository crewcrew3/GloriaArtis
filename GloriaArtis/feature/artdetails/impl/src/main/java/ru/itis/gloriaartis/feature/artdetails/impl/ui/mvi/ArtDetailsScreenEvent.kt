package ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi

internal sealed interface ArtDetailsScreenEvent {
    data class OnInitScreen(val artId: Int): ArtDetailsScreenEvent
    data object OnBackBtnClick: ArtDetailsScreenEvent
}