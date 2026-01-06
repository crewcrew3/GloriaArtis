package ru.itis.gloriaartis.feature.login.impl

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import ru.itis.gloriaartis.domain.repository.AuthRepository
import ru.itis.gloriaartis.feature.login.impl.domain.usecase.LogInUserUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class LogInUserUseCaseTest {

    private val authRepository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: LogInUserUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = LogInUserUseCase(authRepository, dispatcher)
    }

    @Test
    fun `calls logIn with correct params`() = runTest {
        createUseCase(testScheduler)

        val phone = "79276541309"
        val password = "123qwerty"

        useCase(phone, password)

        advanceUntilIdle()

        coVerify {
            authRepository.logIn(
                phoneNumber = phone,
                password = password
            )
        }
    }
}
