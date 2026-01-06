package ru.itis.gloriaartis.domain.repository

import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.domain.model.ArtPreviewModel

interface ArtRepository {
    suspend fun getArtList(
        page: Int,
        pageSize: Int
    ): List<ArtPreviewModel>
    suspend fun getArtDetails(artId: Int): ArtDetailsModel
}