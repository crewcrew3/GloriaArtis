package ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi

import ru.itis.gloriaartis.domain.model.ArtDetailsModel

internal sealed interface ArtDetailsScreenState {
    data object Initial : ArtDetailsScreenState
    data object Loading : ArtDetailsScreenState
    data class ArtDetailsResult(val result: ArtDetailsModel) : ArtDetailsScreenState
}