package ru.itis.gloriaartis.feature.artlist.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.model.ArtPreviewModel
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.ArtRepository
import javax.inject.Inject

internal class GetArtListUseCase @Inject constructor(
    private val artRepository: ArtRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): List<ArtPreviewModel> {
        return withContext(dispatcher) {
            artRepository.getArtList(page, pageSize)
        }
    }
}