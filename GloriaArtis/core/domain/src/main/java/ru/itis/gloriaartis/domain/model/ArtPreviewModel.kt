package ru.itis.gloriaartis.domain.model

data class ArtPreviewModel(
    val id: Int,
    val artTitle: String,
    val artistName: String,
    val imageUrl: String,
    val isHighlight: Boolean,
)