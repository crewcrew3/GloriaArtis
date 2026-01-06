package ru.itis.gloriaartis.feature.artdetails.impl.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.valentinilk.shimmer.shimmer
import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenEffect
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenEvent
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenState
import ru.itis.gloriaartis.ui.BaseScreen
import ru.itis.gloriaartis.ui.R
import ru.itis.gloriaartis.ui.component.DividerCustom
import ru.itis.gloriaartis.ui.component.FieldRow
import ru.itis.gloriaartis.ui.component.ImageCustom
import ru.itis.gloriaartis.ui.component.settings.FieldItemSettings
import ru.itis.gloriaartis.ui.component.settings.IconSettings
import ru.itis.gloriaartis.ui.component.settings.ImageSettings
import ru.itis.gloriaartis.ui.component.settings.TopBarSettings
import ru.itis.gloriaartis.ui.component.shimmer.ShimmerFieldRow
import ru.itis.gloriaartis.ui.theme.DimensionsCustom
import ru.itis.gloriaartis.ui.theme.GloriaArtisTheme
import ru.itis.gloriaartis.ui.theme.IconsCustom
import ru.itis.gloriaartis.ui.theme.StylesCustom

@Composable
internal fun ArtDetailsScreen(
    artId: Int,
    previewArtModel: ArtDetailsModel? = null,
) {

    val viewModel: ArtDetailsViewModel = hiltViewModel()
    val pageState by viewModel.pageState.collectAsState(initial = ArtDetailsScreenState.Initial)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.processEvent(
            event = ArtDetailsScreenEvent.OnInitScreen(
                artId = artId
            )
        )
        viewModel.pageEffect.collect { effect ->
            when (effect) {
                is ArtDetailsScreenEffect.Message -> Toast.makeText(
                    context,
                    context.getText(effect.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    BaseScreen(
        topBarSettings = TopBarSettings(
            text = stringResource(R.string.top_bar_header_art_details)
        ),
        topBarIconSettings = IconSettings(
            onClick = {
                viewModel.processEvent(ArtDetailsScreenEvent.OnBackBtnClick)
            }
        )
    ) { innerPadding ->
        when (pageState) {
            is ArtDetailsScreenState.Initial -> {}
            is ArtDetailsScreenState.Loading -> {
                ShimmerArtDetailsScreen(innerPadding = innerPadding)
            }

            is ArtDetailsScreenState.ArtDetailsResult -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    val art = (pageState as? ArtDetailsScreenState.ArtDetailsResult)?.result
                    //val art = previewArtModel
                    art?.let { art ->
                        item {
                            ImageCustom(
                                imageSettings = ImageSettings(
                                    url = art.imageUrl,
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            )
                            Text(
                                text = art.artTitle,
                                style = StylesCustom.h7,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .padding(top = 32.dp)
                            )
                            Text(
                                text = art.artistName,
                                style = StylesCustom.body7,
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier
                                    .padding(top = 16.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .height(DimensionsCustom.defaultSpacer)
                            )

                            if (art.isHighlight) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .height(DimensionsCustom.highlightMarkCardHeight)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                                        .background(MaterialTheme.colorScheme.surfaceContainer),
                                ) {
                                    Icon(
                                        imageVector = IconsCustom.isHighlightArtIcon(),
                                        tint = MaterialTheme.colorScheme.tertiary,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(DimensionsCustom.iconSizeMaxi)
                                    )
                                    Text(
                                        text = stringResource(R.string.art_details_highlight_label_text),
                                        style = StylesCustom.body3,
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        modifier = Modifier
                                            .padding(8.dp)
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .height(DimensionsCustom.defaultSpacer)
                                )
                            }
                            ArtDetailsFieldsList(art = art)

                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = stringResource(R.string.art_details_additional_images_label),
                                    style = StylesCustom.h8,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier
                                        .padding(top = 16.dp)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(DimensionsCustom.defaultSpacer)
                                )

                                if (art.additionalImages.isEmpty()) {
                                    Text(
                                        text = stringResource(R.string.art_details_no_additional_images),
                                        style = StylesCustom.body3,
                                        color = MaterialTheme.colorScheme.onTertiary,
                                    )
                                } else {
                                    LazyRow(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        items(art.additionalImages) { itemUrl ->
                                            ImageCustom(
                                                imageSettings = ImageSettings(
                                                    url = itemUrl,
                                                    contentScale = ContentScale.FillHeight,
                                                    modifier = Modifier
//                                                        .height(DimensionsCustom.artAddPicHeight)
                                                        .width(DimensionsCustom.artAddPicWidth)
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun ArtDetailsFieldsList(art: ArtDetailsModel) {
    val fields = listOf(
        FieldItemSettings(R.string.art_details_accession_year_label, art.accessionYear),
        FieldItemSettings(R.string.art_details_department_label, art.department),
        FieldItemSettings(R.string.art_details_object_name_label, art.objectName),
        FieldItemSettings(R.string.art_details_culture_label, art.culture),
        FieldItemSettings(R.string.art_details_period_label, art.period),
        FieldItemSettings(R.string.art_details_dynasty_label, art.dynasty),
        FieldItemSettings(R.string.art_details_portfolio_label, art.portfolio),
        FieldItemSettings(R.string.art_details_artist_name_label, art.artistName),
        FieldItemSettings(R.string.art_details_artist_bio_label, art.artistBio),
        FieldItemSettings(R.string.art_details_artist_wikidata_label, art.artistWikidataURL, true),
        FieldItemSettings(R.string.art_details_art_wikidata_label, art.artWikidataURL, true),
        FieldItemSettings(R.string.art_details_medium_label, art.medium)
    )
    fields.forEach { field ->
        FieldRow(field)
        DividerCustom(
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
internal fun ShimmerArtDetailsScreen(innerPadding: PaddingValues) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        item {
            // Картинка
            Box(
                modifier = Modifier
                    .height(DimensionsCustom.artDetailsPicHeight)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                    .shimmer()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Название арта
            Box(
                modifier = Modifier
                    .height(DimensionsCustom.shimmerTextHeightTitle)
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                    .shimmer()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Имя художника
            Box(
                modifier = Modifier
                    .height(DimensionsCustom.shimmerTextHeightSubTitle)
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                    .shimmer()
                    .background(Color.Gray.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(DimensionsCustom.defaultSpacer))
        }

        // Каждый элемент FieldRow в ArtDetailsFieldsList
        items(12) {
            ShimmerFieldRow()
            DividerCustom(
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
        }

        item {
            Spacer(modifier = Modifier.height(DimensionsCustom.defaultSpacer))

            // additionalImages
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(3) {
                    Box(
                        modifier = Modifier
                            .height(DimensionsCustom.artAddPicHeight)
                            .width(DimensionsCustom.artAddPicWidth)
                            .clip(RoundedCornerShape(DimensionsCustom.roundedCornersSmall))
                            .shimmer()
                            .background(Color.Gray.copy(alpha = 0.3f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(DimensionsCustom.defaultSpacer))
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
internal fun ArtDetailsScreenPreview() {
    GloriaArtisTheme {
        ArtDetailsScreen(
            artId = 1,
            previewArtModel = ArtDetailsModel(
                id = 1,
                artTitle = "Wheat Field with Cypresses",
                artistName = "Vincent van Gogh",
                imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                isHighlight = true,
                additionalImages = listOf(
                    "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig"
                ),
                accessionYear = "1921",
                department = "Egyptian Art",
                objectName = "Painting",
                culture = "British",
                period = "Middle Bronze Age",
                dynasty = "Kingdom of Benin",
                portfolio = "Birds of America",
                artistBio = "Dutch, Zundert 1853–1890 Auvers-sur-Oise",
                artistWikidataURL = "https://www.wikidata.org/wiki/Q694774",
                artWikidataURL = "https://www.wikidata.org/wiki/Q432253",
                medium = "Oil on canvas"
            )
        )
    }
}