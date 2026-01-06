package ru.itis.gloriaartis.utils

import ru.itis.gloriaartis.ui.R
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionHandler @Inject constructor() {
    fun handleExceptionMessage(exceptionMessage: String?) : Int {
        return when (exceptionMessage) {

            ExceptionCode.ART_RESPONSE -> R.string.exception_msg_art_response
            ExceptionCode.GET_PROFILE_ERROR -> R.string.exception_msg_profile
            ExceptionCode.WRONG_CREDENTIALS -> R.string.exception_msg_auth_wrong_credentials
            ExceptionCode.USER_ALREADY_EXISTS -> R.string.exception_msg_user_already_exists

            ExceptionCode.NOT_FOUND -> R.string.exception_msg_not_found
            ExceptionCode.FORBIDDEN -> R.string.exception_msg_forbidden
            ExceptionCode.BAD_REQUEST -> R.string.exception_msg_bad_request
            ExceptionCode.UNAUTHORIZED -> R.string.exception_msg_unauthorized
            ExceptionCode.GONE -> R.string.exception_msg_common
            ExceptionCode.CLIENT_TIMEOUT -> R.string.exception_msg_common
            ExceptionCode.PAYMENT_REQUIRED -> R.string.exception_msg_common
            ExceptionCode.SERVER_ERROR -> R.string.exception_msg_common

            else -> R.string.exception_msg_common
        }
    }
}