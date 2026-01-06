package ru.itis.gloriaartis.data.impl.remote.mapper

import ru.itis.gloriaartis.data.impl.remote.model.response.ArtSearchResponse
import ru.itis.gloriaartis.domain.model.ArtSearchModel
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import javax.inject.Inject

internal class ArtSearchResponseToModelMapper @Inject constructor() {
    fun map(input: ArtSearchResponse?): ArtSearchModel {
        val response = requireNotNull(input) { ExceptionCode.ART_RESPONSE }
        return ArtSearchModel(
            total = requireNotNull(response.total) { ExceptionCode.ART_RESPONSE },
            objectIDs = requireNotNull(response.objectIDs) { ExceptionCode.ART_RESPONSE }
                .map { id -> requireNotNull(id) { ExceptionCode.ART_RESPONSE} },
        )
    }
}