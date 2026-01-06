package ru.itis.gloriaartis.impl.navigators

import ru.itis.gloriaartis.api.BottomBarNavigator
import ru.itis.gloriaartis.feature.artlist.api.ArtListNavKey
import ru.itis.gloriaartis.feature.profile.api.ProfileNavKey
import ru.itis.gloriaartis.impl.BackStackHolder
import javax.inject.Inject

class BottomBarNavigatorImpl @Inject constructor(
    private val backStackHolder: BackStackHolder
) : BottomBarNavigator {

    override fun toArtListScreen() {
        backStackHolder.backStack?.add(ArtListNavKey)
    }

    override fun toProfileScreen() {
        backStackHolder.backStack?.add(ProfileNavKey)
    }
}