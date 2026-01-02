package ru.itis.gloriaartis.ui.component.settings

import androidx.compose.ui.graphics.Color

data class ArtListItemSettings(
    val id: Int,
    val artTitle: String,
    val artistName: String,
    val imageUrl: String,
    val isHighlight: Boolean,
    val cardContainerColor: Color? = null,
    val onClick: () -> Unit = {},
)