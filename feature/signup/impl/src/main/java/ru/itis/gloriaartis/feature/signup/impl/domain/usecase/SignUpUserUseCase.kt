package ru.itis.gloriaartis.feature.signup.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.AuthRepository
import javax.inject.Inject

internal class SignUpUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        nickname: String,
        phoneNumber: String,
        password: String,
        repeatPassword: String
    ) {
        withContext(dispatcher) {
            authRepository.signUp(
                nickname = nickname,
                phoneNumber = phoneNumber,
                password = password,
                repeatPassword = repeatPassword
            )
        }
    }
}