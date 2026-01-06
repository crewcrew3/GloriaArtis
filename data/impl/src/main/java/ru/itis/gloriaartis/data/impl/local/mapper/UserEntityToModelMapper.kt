package ru.itis.gloriaartis.data.impl.local.mapper

import ru.itis.gloriaartis.data.impl.local.entity.UserEntity
import ru.itis.gloriaartis.domain.model.UserModel
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import javax.inject.Inject

internal class UserEntityToModelMapper @Inject constructor() {
    fun map(input: UserEntity?): UserModel {
        val entity = requireNotNull(input) { ExceptionCode.GET_PROFILE_ERROR }
        return UserModel(
            id = entity.id,
            nickname = entity.userNickname,
            phoneNumber = entity.userPhoneNumber,
            imageUrl = entity.userImageUrl ?: ""
        )
    }
}