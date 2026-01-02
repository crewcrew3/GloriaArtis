package ru.itis.gloriaartis.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.itis.gloriaartis.ui.R
import ru.itis.gloriaartis.ui.component.settings.FieldItemSettings
import ru.itis.gloriaartis.ui.theme.StylesCustom

@Composable
fun FieldRow(
    fieldSettings: FieldItemSettings
) {

    val uriHandler = LocalUriHandler.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(fieldSettings.labelRes),
            style = StylesCustom.h4,
            color = MaterialTheme.colorScheme.onSecondary
        )

        if (fieldSettings.isLink && fieldSettings.value.isNotBlank()) {
            Text(
                text = stringResource(R.string.art_details_go_to_link),
                style = StylesCustom.body3Underline,
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(fieldSettings.value)
                    }
            )
        } else {
            Text(
                text = fieldSettings.value,
                style = StylesCustom.body3,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.End,
            )
        }
    }
}
