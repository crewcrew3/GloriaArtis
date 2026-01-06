package ru.itis.gloriaartis.feature.artlist.impl

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
import ru.itis.gloriaartis.domain.model.ArtPreviewModel
import ru.itis.gloriaartis.domain.repository.ArtRepository
import ru.itis.gloriaartis.feature.artlist.impl.domain.usecase.GetArtListUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class GetArtListUseCaseTest {

    private val artRepository: ArtRepository = mockk()
    private lateinit var useCase: GetArtListUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = GetArtListUseCase(artRepository, dispatcher)
    }

    @Test
    fun `returns art list from repository`() = runTest {
        createUseCase(testScheduler)

        val page = 0
        val pageSize = 20
        val expected = listOf(mockk<ArtPreviewModel>())

        coEvery { artRepository.getArtList(page, pageSize) } returns expected

        val result = useCase(page, pageSize)

        advanceUntilIdle()

        assertEquals(expected, result)
        coVerify { artRepository.getArtList(page, pageSize) }
    }
}
