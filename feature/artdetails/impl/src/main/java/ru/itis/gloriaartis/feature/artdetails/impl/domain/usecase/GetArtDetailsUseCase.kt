package ru.itis.gloriaartis.feature.artdetails.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.ArtRepository
import javax.inject.Inject

internal class GetArtDetailsUseCase @Inject constructor(
    private val artRepository: ArtRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(artId:Int): ArtDetailsModel {
        return withContext(dispatcher) {
            artRepository.getArtDetails(artId)
        }
    }
}