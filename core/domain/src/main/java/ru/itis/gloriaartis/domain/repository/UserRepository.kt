package ru.itis.gloriaartis.domain.repository

import ru.itis.gloriaartis.domain.model.UserModel

interface UserRepository {
    suspend fun getCurrentUser(): UserModel
}