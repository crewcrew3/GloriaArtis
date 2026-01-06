package ru.itis.gloriaartis.impl.navigators

import ru.itis.gloriaartis.api.ArtNavigator
import ru.itis.gloriaartis.feature.artdetails.api.ArtDetailsNavKey
import ru.itis.gloriaartis.impl.BackStackHolder
import javax.inject.Inject

class ArtNavigatorImpl @Inject constructor(
    private val backStackHolder: BackStackHolder
) : ArtNavigator {

    override fun toArtDetailsScreen(artId: Int) {
        backStackHolder.backStack?.add(ArtDetailsNavKey(artId = artId))
    }

    override fun back() {
        backStackHolder.backStack?.removeLastOrNull()
    }
}