package ru.itis.gloriaartis.feature.artlist.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.gloriaartis.api.ArtNavigator
import ru.itis.gloriaartis.api.BottomBarNavigator
import ru.itis.gloriaartis.utils.ExceptionHandler
import ru.itis.gloriaartis.feature.artlist.impl.domain.usecase.GetArtListUseCase
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEffect
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEvent
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenState
import ru.itis.gloriaartis.utils.ScreenAnalytics
import javax.inject.Inject

@HiltViewModel
internal class ArtListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase,
    private val artNavigator: ArtNavigator,
    private val bottomBarNavigator: BottomBarNavigator,
    private val exceptionHandler: ExceptionHandler,
) : ViewModel() {

    init {
        ScreenAnalytics.logScreen(this::class.java.simpleName)
    }

    private val _pageState = MutableStateFlow<ArtListScreenState>(value = ArtListScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    private val _pageEffect = MutableSharedFlow<ArtListScreenEffect>()
    val pageEffect = _pageEffect.asSharedFlow()

    private val pageSize = 15
    private var currentPage = 0
    private var isLoading = false

    fun processEvent(event: ArtListScreenEvent) {
        when (event) {
            is ArtListScreenEvent.OnScreenInit -> loadNextPage()
            is ArtListScreenEvent.OnItemClick -> artNavigator.toArtDetailsScreen(event.artId)
            is ArtListScreenEvent.OnProfileBottomBarClick -> bottomBarNavigator.toProfileScreen()
        }
    }

    fun loadNextPage() {
        if (isLoading) return //чтобы не загружалась одна и та же страница несколько раз

        viewModelScope.launch {
            isLoading = true
            val currentList = (pageState.value as? ArtListScreenState.ArtsResult)?.result ?: emptyList()
            runCatching {
                _pageState.value = ArtListScreenState.Loading
                getArtListUseCase(currentPage, pageSize)
            }.onSuccess { newItems ->
                currentPage++
                _pageState.value = ArtListScreenState.ArtsResult(
                    result = currentList + newItems
                )
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ArtListScreenEffect.Message(
                        message = messageResId
                    )
                )
            }
            isLoading = false
        }
    }
}