package ru.itis.gloriaartis.feature.profile.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

import ru.itis.gloriaartis.domain.repository.AuthRepository
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.CheckIsUserAuthUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class CheckIsUserAuthUseCaseTest {

    private val authRepository: AuthRepository = mockk()
    private lateinit var useCase: CheckIsUserAuthUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = CheckIsUserAuthUseCase(authRepository, dispatcher)
    }

    @Test
    fun `returns auth state`() = runTest {
        createUseCase(testScheduler)

        coEvery { authRepository.isUserAuth() } returns true

        val result = useCase()

        advanceUntilIdle()

        assertTrue(result)
        coVerify { authRepository.isUserAuth() }
    }
}
