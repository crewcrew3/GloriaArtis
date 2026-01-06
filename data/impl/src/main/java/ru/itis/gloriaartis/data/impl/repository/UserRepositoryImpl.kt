package ru.itis.gloriaartis.data.impl.repository

import ru.itis.gloriaartis.data.impl.local.dao.UserDao
import ru.itis.gloriaartis.data.impl.local.mapper.UserEntityToModelMapper
import ru.itis.gloriaartis.domain.local.storage.BasicUserDataStorage
import ru.itis.gloriaartis.domain.model.UserModel
import ru.itis.gloriaartis.domain.repository.UserRepository
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import java.lang.IllegalArgumentException
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val basicUserDataStorage: BasicUserDataStorage,
    private val userDao: UserDao,
    private val userEntityToModelMapper: UserEntityToModelMapper,
) : UserRepository {

    override suspend fun getCurrentUser(): UserModel {
        val phoneNumber = basicUserDataStorage.getUserPhoneNumber() ?: throw IllegalArgumentException(ExceptionCode.UNAUTHORIZED)
        val user = userDao.getUserByPhoneNumber(phoneNumber)
        return user.let(userEntityToModelMapper::map)
    }
}