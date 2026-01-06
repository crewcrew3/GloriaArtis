package ru.itis.gloriaartis.feature.artdetails.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.domain.repository.ArtRepository
import ru.itis.gloriaartis.feature.artdetails.impl.domain.usecase.GetArtDetailsUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class GetArtDetailsUseCaseTest {

    private val artRepository: ArtRepository = mockk()
    private lateinit var useCase: GetArtDetailsUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = GetArtDetailsUseCase(artRepository, dispatcher)
    }

    @Test
    fun `returns art details from repository`() = runTest {
        createUseCase(testScheduler)

        val artId = 1
        val expected = mockk<ArtDetailsModel>()

        coEvery { artRepository.getArtDetails(artId) } returns expected

        val result = useCase(artId)

        advanceUntilIdle()

        assertEquals(expected, result)
        coVerify { artRepository.getArtDetails(artId) }
    }
}
