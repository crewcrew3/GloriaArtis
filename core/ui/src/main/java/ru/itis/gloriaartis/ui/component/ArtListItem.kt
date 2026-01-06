package ru.itis.gloriaartis.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.gloriaartis.ui.component.settings.ArtListItemSettings
import ru.itis.gloriaartis.ui.component.settings.ImageSettings
import ru.itis.gloriaartis.ui.theme.DimensionsCustom
import ru.itis.gloriaartis.ui.theme.GloriaArtisTheme
import ru.itis.gloriaartis.ui.theme.IconsCustom
import ru.itis.gloriaartis.ui.theme.StylesCustom

@Composable
fun ArtListItem(
    itemSettings: ArtListItemSettings,
) {
    Card(
        modifier = Modifier
            .height(DimensionsCustom.artCardHeight)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = itemSettings.onClick),
        colors = CardDefaults.cardColors(
            containerColor = itemSettings.cardContainerColor ?: Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                ImageCustom(
                    imageSettings = ImageSettings(
                        url = itemSettings.imageUrl,
                        //contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(DimensionsCustom.artPicHeight)
                            .width(DimensionsCustom.artPicWidth)
                    ),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = itemSettings.artTitle,
                    style = StylesCustom.h2,
                    color = MaterialTheme.colorScheme.onSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                )
                Text(
                    text = itemSettings.artistName,
                    style = StylesCustom.body4Light,
                    color = MaterialTheme.colorScheme.onTertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp)
                )
            }
            if (itemSettings.isHighlight) { 
                Icon(
                    imageVector = IconsCustom.isHighlightArtIcon(),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .height(DimensionsCustom.iconSize)
                        .width(DimensionsCustom.iconSize)
                )
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun ArtListItemPreview() {
    GloriaArtisTheme {
        ArtListItem(
            itemSettings = ArtListItemSettings(
                id = 1,
                artTitle = "Dark Night",
                artistName = "Vincent Leonardo Dali",
                imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                isHighlight = true,
            ),
        )
    }
}
