package ru.itis.gloriaartis.ui.component.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import ru.itis.gloriaartis.ui.theme.DimensionsCustom

@Composable
fun ShimmerFieldRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Левый блок (label)
        Box(
            modifier = Modifier
                .weight(0.5f)
                .height(DimensionsCustom.shimmerTextHeight)
                .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                .shimmer()
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        Spacer(modifier = Modifier.width(160.dp))

        // Правый блок (value)
        Box(
            modifier = Modifier
                .weight(0.5f)
                .height(DimensionsCustom.shimmerTextHeight)
                .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                .shimmer()
                .background(Color.Gray.copy(alpha = 0.3f))
        )
    }
}
