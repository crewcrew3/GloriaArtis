package ru.itis.gloriaartis.ui.component.settings

import androidx.annotation.StringRes

data class FieldItemSettings(
    @StringRes val labelRes: Int,
    val value: String,
    val isLink: Boolean = false,
)
