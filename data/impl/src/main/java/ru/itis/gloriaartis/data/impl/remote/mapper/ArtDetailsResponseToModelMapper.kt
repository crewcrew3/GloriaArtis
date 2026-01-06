package ru.itis.gloriaartis.data.impl.remote.mapper

import ru.itis.gloriaartis.data.impl.remote.model.response.ArtResponse
import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import javax.inject.Inject

internal class ArtDetailsResponseToModelMapper @Inject constructor() {
    fun map(input: ArtResponse?): ArtDetailsModel {
        val response = requireNotNull(input) { ExceptionCode.ART_RESPONSE }
        return ArtDetailsModel(
            id = requireNotNull(response.id) { ExceptionCode.ART_RESPONSE },
            artTitle = requireNotNull(response.artTitle) { ExceptionCode.ART_RESPONSE },
            artistName = requireNotNull(response.artistName) { ExceptionCode.ART_RESPONSE },
            imageUrl = requireNotNull(response.imageUrl) { ExceptionCode.ART_RESPONSE},
            isHighlight = requireNotNull(response.isHighlight) { ExceptionCode.ART_RESPONSE},
            accessionYear = requireNotNull(response.accessionYear) { ExceptionCode.ART_RESPONSE},
            department = requireNotNull(response.department) { ExceptionCode.ART_RESPONSE},
            objectName = requireNotNull(response.objectName) { ExceptionCode.ART_RESPONSE},
            culture = requireNotNull(response.culture) { ExceptionCode.ART_RESPONSE},
            dynasty = requireNotNull(response.dynasty) { ExceptionCode.ART_RESPONSE},
            period = requireNotNull(response.period) { ExceptionCode.ART_RESPONSE},
            portfolio = requireNotNull(response.portfolio) { ExceptionCode.ART_RESPONSE},
            artistBio = requireNotNull(response.artistBio) { ExceptionCode.ART_RESPONSE},
            artWikidataURL = requireNotNull(response.artWikidataURL) { ExceptionCode.ART_RESPONSE},
            artistWikidataURL = requireNotNull(response.artistWikidataURL) { ExceptionCode.ART_RESPONSE},
            medium = requireNotNull(response.medium) { ExceptionCode.ART_RESPONSE},
            additionalImages = requireNotNull(response.additionalImages) { ExceptionCode.ART_RESPONSE}.map { url ->
                requireNotNull(url) {
                    ExceptionCode.ART_RESPONSE
                }
            }
        )
    }
}