package ru.itis.gloriaartis.impl.navigators

import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.feature.login.api.LoginNavKey
import ru.itis.gloriaartis.feature.signup.api.SignUpNavKey
import ru.itis.gloriaartis.impl.BackStackHolder
import javax.inject.Inject

class ProfileNavigatorImpl @Inject constructor(
    private val backStackHolder: BackStackHolder
) : ProfileNavigator {
    override fun toLogInScreen() {
        backStackHolder.backStack?.add(LoginNavKey)
    }

    override fun toSighUpScreen() {
        backStackHolder.backStack?.add(SignUpNavKey)
    }

    override fun back() {
        backStackHolder.backStack?.removeLastOrNull()
    }
}