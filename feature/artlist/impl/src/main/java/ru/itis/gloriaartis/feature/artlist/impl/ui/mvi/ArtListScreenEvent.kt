package ru.itis.gloriaartis.feature.artlist.impl.ui.mvi

internal sealed interface ArtListScreenEvent {
    data object OnScreenInit : ArtListScreenEvent
    data class OnItemClick(val artId: Int) : ArtListScreenEvent
    data object OnProfileBottomBarClick : ArtListScreenEvent
}