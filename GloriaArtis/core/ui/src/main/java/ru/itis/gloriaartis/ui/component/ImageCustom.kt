package ru.itis.gloriaartis.ui.component

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import ru.itis.gloriaartis.ui.component.settings.ImageSettings

@Composable
fun ImageCustom(
    imageSettings: ImageSettings,
) {
    AsyncImage(
        model = imageSettings.url,
        contentDescription = imageSettings.contentDescription,
        modifier = imageSettings.modifier
            .clip(imageSettings.shape)
            .background(imageSettings.backgroundColor ?: MaterialTheme.colorScheme.surfaceContainerHigh),
        contentScale = imageSettings.contentScale,
    )
}