package ru.itis.gloriaartis.feature.artdetails.impl.ui

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
import ru.itis.gloriaartis.feature.artdetails.impl.domain.usecase.GetArtDetailsUseCase
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenEffect
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenEvent
import ru.itis.gloriaartis.feature.artdetails.impl.ui.mvi.ArtDetailsScreenState
import ru.itis.gloriaartis.utils.PerformanceCustomTracer
import ru.itis.gloriaartis.utils.ScreenAnalytics
import ru.itis.gloriaartis.utils.properties.OtherProperties
import javax.inject.Inject

@HiltViewModel
internal class ArtDetailsViewModel @Inject constructor(
    private val getArtDetailsUseCase: GetArtDetailsUseCase,
    private val artNavigator: ArtNavigator,
    private val exceptionHandler: ExceptionHandler,
    private val performanceCustomTracer: PerformanceCustomTracer,
) : ViewModel() {

    init {
        ScreenAnalytics.logScreen(this::class.java.simpleName)
    }

    private val _pageState = MutableStateFlow<ArtDetailsScreenState>(value = ArtDetailsScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    private val _pageEffect = MutableSharedFlow<ArtDetailsScreenEffect>()
    val pageEffect = _pageEffect.asSharedFlow()

    fun processEvent(event: ArtDetailsScreenEvent) {
        when(event) {
            is ArtDetailsScreenEvent.OnInitScreen -> getArtDetails(event.artId)
            is ArtDetailsScreenEvent.OnBackBtnClick -> artNavigator.back()
        }
    }

    private fun getArtDetails(artId: Int) {
        viewModelScope.launch {
            val traceName = OtherProperties.PERF_TRACE_ART_DETAILS_PREFIX + artId
            runCatching {
                _pageState.value = ArtDetailsScreenState.Loading
                performanceCustomTracer.startTrace(traceName = traceName)
                getArtDetailsUseCase(artId)
            }.onSuccess { artModel ->
                _pageState.value = ArtDetailsScreenState.ArtDetailsResult(result = artModel)
                performanceCustomTracer.stopTrace(traceName)
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ArtDetailsScreenEffect.Message(
                        message = messageResId
                    )
                )
                performanceCustomTracer.stopTrace(traceName)
            }
        }
    }
}