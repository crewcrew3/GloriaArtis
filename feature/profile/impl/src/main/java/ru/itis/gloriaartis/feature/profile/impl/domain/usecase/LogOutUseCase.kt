package ru.itis.gloriaartis.feature.profile.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.AuthRepository
import javax.inject.Inject

internal class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke() {
        return withContext(dispatcher) {
            authRepository.logOut()
        }
    }
}