package ru.itis.gloriaartis.feature.signup.impl

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import ru.itis.gloriaartis.domain.repository.AuthRepository
import ru.itis.gloriaartis.feature.signup.impl.domain.usecase.SignUpUserUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class SignUpUserUseCaseTest {

    private val authRepository: AuthRepository = mockk(relaxed = true)
    private lateinit var useCase: SignUpUserUseCase

    private fun createUseCase(scheduler: TestCoroutineScheduler) {
        val dispatcher = StandardTestDispatcher(scheduler)
        useCase = SignUpUserUseCase(authRepository, dispatcher)
    }

    @Test
    fun `calls signUp with correct params`() = runTest {
        createUseCase(testScheduler)

        val nickname = "Nick"
        val phone = "79276541309"
        val password = "123qwerty"

        useCase(
            nickname = nickname,
            phoneNumber = phone,
            password = password,
            repeatPassword = password
        )

        advanceUntilIdle()

        coVerify {
            authRepository.signUp(
                nickname = nickname,
                phoneNumber = phone,
                password = password,
                repeatPassword = password
            )
        }
    }
}
