package ru.itis.gloriaartis.feature.profile.impl.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.itis.gloriaartis.domain.model.UserModel
import ru.itis.gloriaartis.domain.qualifiers.IoDispatchers
import ru.itis.gloriaartis.domain.repository.UserRepository
import javax.inject.Inject

internal class GetProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): UserModel {
        return withContext(dispatcher) {
            userRepository.getCurrentUser()
        }
    }
}