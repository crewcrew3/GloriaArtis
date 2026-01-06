package ru.itis.gloriaartis.ui.component.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.size.Dimension
import com.valentinilk.shimmer.shimmer
import ru.itis.gloriaartis.ui.theme.DimensionsCustom

@Composable
fun ShimmerArtListItem() {
    Card(
        modifier = Modifier
            .height(DimensionsCustom.artCardHeight)
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Блок картинки
            Box(
                modifier = Modifier
                    .height(DimensionsCustom.artPicHeight)
                    .width(DimensionsCustom.artPicWidth)
                    .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                    .shimmer()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.width(DimensionsCustom.defaultSpacer))

            // Колонка текста
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Заголовок (Art Title)
                Box(
                    modifier = Modifier
                        .height(DimensionsCustom.shimmerTextHeightTitleCard) // используем высоту из DimensionsCustom
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                        .shimmer()
                        .background(Color.Gray.copy(alpha = 0.3f))
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Подзаголовок (Artist Name)
                Box(
                    modifier = Modifier
                        .height(DimensionsCustom.shimmerTextHeightSubtitleCard) // чуть меньше высоты текста
                        .fillMaxWidth(0.6f)
                        .shimmer()
                        .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                        .background(Color.Gray.copy(alpha = 0.3f))
                )
            }

            Spacer(modifier = Modifier.width(DimensionsCustom.defaultSpacer))

            // Иконка выделения (если есть)
            Box(
                modifier = Modifier
                    .size(DimensionsCustom.iconSize)
                    .shimmer()
                    .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                    .background(Color.Gray.copy(alpha = 0.3f))
            )
        }
    }
}
