package ru.itis.gloriaartis.feature.login.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.AuthRepository
import javax.inject.Inject

internal class LogInUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        phoneNumber: String,
        password: String,
    ) {
        withContext(dispatcher) {
            authRepository.logIn(
                phoneNumber = phoneNumber,
                password = password,
            )
        }
    }
}