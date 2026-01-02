package ru.itis.gloriaartis.feature.artdetails.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailsNavKey(
    val artId: Int,
) : NavKey