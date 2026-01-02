package ru.itis.gloriaartis.impl

import ru.itis.gloriaartis.api.ArtNavigator
import ru.itis.gloriaartis.feature.artdetails.api.ArtDetailsNavKey
import javax.inject.Inject

class ArtNavigatorImpl @Inject constructor(
    private val backStack: MutableList<Any>
) : ArtNavigator {

    override fun toArtDetailsScreen(artId: Int) {
        backStack.add(ArtDetailsNavKey(artId = artId))
    }

    override fun back() {
        backStack.removeLastOrNull()
    }
}
