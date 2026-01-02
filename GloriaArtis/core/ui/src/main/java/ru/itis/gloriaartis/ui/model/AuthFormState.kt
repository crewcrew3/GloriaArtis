package ru.itis.gloriaartis.ui.model

data class AuthFormState(
    val nickname: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)