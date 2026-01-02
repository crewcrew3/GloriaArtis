package ru.itis.gloriaartis.utils

import ru.itis.gloriaartis.ui.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionHandler @Inject constructor(
    //private val artNavigator: ArtNavigator
) {
    fun handleExceptionMessage(exceptionMessage: String?) : Int {
        return when (exceptionMessage) {
            else -> R.string.exception_msg_common
        }
    }
}