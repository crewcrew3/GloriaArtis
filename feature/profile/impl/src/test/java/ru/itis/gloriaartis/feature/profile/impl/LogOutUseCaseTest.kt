package ru.itis.gloriaartis.feature.profile.impl

import org.junit.Test
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import ru.itis.gloriaartis.domain.repository.AuthRepository
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.LogOutUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class LogOutUseCaseTest {

    private val authRepository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: LogOutUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = LogOutUseCase(authRepository, dispatcher)
    }

    @Test
    fun `calls logOut`() = runTest {
        createUseCase(testScheduler)

        useCase()

        advanceUntilIdle()

        coVerify { authRepository.logOut() }
    }
}
