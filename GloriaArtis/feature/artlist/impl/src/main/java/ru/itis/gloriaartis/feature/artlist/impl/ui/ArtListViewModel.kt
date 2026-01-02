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
import ru.itis.gloriaartis.utils.ExceptionHandler
import ru.itis.gloriaartis.feature.artlist.impl.domain.usecase.GetArtListUseCase
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEffect
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenEvent
import ru.itis.gloriaartis.feature.artlist.impl.ui.mvi.ArtListScreenState
import javax.inject.Inject

@HiltViewModel
internal class ArtListViewModel @Inject constructor(
    private val getArtListUseCase: GetArtListUseCase,
    private val artNavigator: ArtNavigator,
    private val exceptionHandler: ExceptionHandler,
) : ViewModel() {

    private val _pageState = MutableStateFlow<ArtListScreenState>(value = ArtListScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    private val _pageEffect = MutableSharedFlow<ArtListScreenEffect>()
    val pageEffect = _pageEffect.asSharedFlow()

    var numberOfLoadingItems = 10

    fun processEvent(event: ArtListScreenEvent) {
        when (event) {
            is ArtListScreenEvent.OnScreenInit -> getArtList()
            is ArtListScreenEvent.OnItemClick -> artNavigator.toArtDetailsScreen(event.artId)
        }
    }

    private fun getArtList() {
        viewModelScope.launch {
            runCatching {
                _pageState.value = ArtListScreenState.Loading
                //delay(2000)
                getArtListUseCase()
            }.onSuccess { result ->
                _pageState.value = ArtListScreenState.ArtsResult(result = result)
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ArtListScreenEffect.Message(
                        message = messageResId
                    )
                )
            }
        }.also { numberOfLoadingItems = 0 }
    }
}