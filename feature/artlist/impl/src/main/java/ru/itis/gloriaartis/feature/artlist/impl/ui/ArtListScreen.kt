package ru.itis.gloriaartis.feature.artlist.impl.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.itis.gloriaartis.domain.model.ArtPreviewModel
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEffect
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEvent
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenState
import ru.itis.gloriaartis.ui.BaseScreen
import ru.itis.gloriaartis.ui.R
import ru.itis.gloriaartis.ui.component.ArtListItem
import ru.itis.gloriaartis.ui.component.DividerCustom
import ru.itis.gloriaartis.ui.component.settings.ArtListItemSettings
import ru.itis.gloriaartis.ui.component.settings.BottomBarSettings
import ru.itis.gloriaartis.ui.component.shimmer.ShimmerArtListItem
import ru.itis.gloriaartis.ui.theme.GloriaArtisTheme
import ru.itis.gloriaartis.ui.theme.StylesCustom

@Composable
internal fun ArtListScreen(
    previewList: List<ArtPreviewModel> = emptyList()
) {

    val viewModel: ArtListViewModel = hiltViewModel()
    val pageState by viewModel.pageState.collectAsState(initial = ArtListScreenState.Initial)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.processEvent(ArtListScreenEvent.OnScreenInit)
        viewModel.pageEffect.collect { effect ->
            when (effect) {
                is ArtListScreenEffect.Message -> Toast.makeText(context, context.getText(effect.message), Toast.LENGTH_SHORT).show()
            }
        }
    }

    BaseScreen(
        bottomBarSettings = BottomBarSettings(
            onArtListClick = { }, //мы уже тут
            onProfileClick = {
                viewModel.processEvent(
                    ArtListScreenEvent.OnProfileBottomBarClick
                )
            },
        )
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                Text(
                    text = stringResource(R.string.title_art_list),
                    style = StylesCustom.h5,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, bottom = 16.dp)
                )
                DividerCustom(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }
            when (pageState) {
                is ArtListScreenState.Initial -> { }
                is ArtListScreenState.Loading -> {
                    items(7) {
                        ShimmerArtListItem()
                    }
                }

                is ArtListScreenState.ArtsResult -> {
                    val list = (pageState as ArtListScreenState.ArtsResult).result
                    itemsIndexed(list) { index, art ->
                        ArtListItem(
                            itemSettings = ArtListItemSettings(
                                id = art.id,
                                artTitle = art.artTitle,
                                artistName = art.artistName,
                                imageUrl = art.imageUrl,
                                isHighlight = art.isHighlight,
                                onClick = {
                                    viewModel.processEvent(
                                        ArtListScreenEvent.OnItemClick(
                                            artId = art.id
                                        )
                                    )
                                }
                            ),
                        )
                        if (index == list.lastIndex) {
                            viewModel.loadNextPage()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
internal fun ArtListScreenPreview() {
    GloriaArtisTheme {
        ArtListScreen(
            previewList = listOf(
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
                ArtPreviewModel(
                    id = 1,
                    artTitle = "Dark Night",
                    artistName = "Vincent Leonardo Dali",
                    imageUrl = "https://avatars.mds.yandex.net/get-mpic/10255013/2a00000196bd7bbb3a41911c3db5b2829ac5/orig",
                    isHighlight = true,
                ),
            )
        )
    }
}