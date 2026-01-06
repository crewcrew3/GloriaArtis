package ru.itis.gloriaartis.feature.profile.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

import ru.itis.gloriaartis.domain.model.UserModel
import ru.itis.gloriaartis.domain.repository.UserRepository
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.GetProfileUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileUseCaseTest {

    private val userRepository: UserRepository = mockk()
    private lateinit var useCase: GetProfileUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = GetProfileUseCase(userRepository, dispatcher)
    }

    @Test
    fun `returns current user`() = runTest {
        createUseCase(testScheduler)

        val expected = mockk<UserModel>()

        coEvery { userRepository.getCurrentUser() } returns expected

        val result = useCase()

        advanceUntilIdle()

        assertEquals(expected, result)
        coVerify { userRepository.getCurrentUser() }
    }
}
