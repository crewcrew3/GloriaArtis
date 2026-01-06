package ru.itis.gloriaartis.data.impl.remote.mapper

import ru.itis.gloriaartis.data.impl.remote.model.response.ArtResponse
import ru.itis.gloriaartis.domain.model.ArtPreviewModel
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import javax.inject.Inject

internal class ArtPreviewResponseToModelMapper @Inject constructor() {
    fun map(input: ArtResponse?): ArtPreviewModel {
        val response = requireNotNull(input) { ExceptionCode.ART_RESPONSE }
        return ArtPreviewModel(
            id = requireNotNull(response.id) { ExceptionCode.ART_RESPONSE },
            artTitle = requireNotNull(response.artTitle) { ExceptionCode.ART_RESPONSE },
            artistName = requireNotNull(response.artistName) { ExceptionCode.ART_RESPONSE },
            imageUrl = requireNotNull(response.imageUrl) { ExceptionCode.ART_RESPONSE},
            isHighlight = requireNotNull(response.isHighlight) { ExceptionCode.ART_RESPONSE},
        )
    }
}