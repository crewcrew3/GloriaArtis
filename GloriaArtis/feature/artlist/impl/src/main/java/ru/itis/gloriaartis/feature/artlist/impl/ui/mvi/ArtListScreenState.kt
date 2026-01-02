package ru.itis.gloriaartis.feature.artlist.impl.ui.mvi

import ru.itis.gloriaartis.domain.model.ArtPreviewModel

internal sealed interface ArtListScreenState {
    data object Initial : ArtListScreenState
    data object Loading : ArtListScreenState
    data class ArtsResult(val result: List<ArtPreviewModel>) : ArtListScreenState
}