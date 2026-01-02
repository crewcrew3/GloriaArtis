package ru.itis.gloriaartis.impl

import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.feature.login.api.LoginNavKey
import ru.itis.gloriaartis.feature.signup.api.SignUpNavKey
import javax.inject.Inject

class ProfileNavigatorImpl @Inject constructor(
    private val backStack: MutableList<Any>
) : ProfileNavigator {
    override fun toLogInScreen() {
        backStack.add(LoginNavKey)
    }

    override fun toSighUpScreen() {
        backStack.add(SignUpNavKey)
    }

    override fun back() {
        backStack.removeLastOrNull()
    }
}