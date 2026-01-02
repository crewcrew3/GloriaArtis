package ru.itis.gloriaartis.feature.profile.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.utils.ExceptionHandler
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.CheckIsUserAuthUseCase
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.GetProfileUseCase
import ru.itis.gloriaartis.feature.profile.impl.domain.usecase.LogOutUseCase
import ru.itis.gloriaartis.feature.profile.impl.ui.mvi.ProfileScreenEffect
import ru.itis.gloriaartis.feature.profile.impl.ui.mvi.ProfileScreenEvent
import ru.itis.gloriaartis.feature.profile.impl.ui.mvi.ProfileScreenState
import javax.inject.Inject

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val checkIsUserAuthUseCase: CheckIsUserAuthUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val profileNavigator: ProfileNavigator,
    private val exceptionHandler: ExceptionHandler,
) : ViewModel() {

    private val _pageState = MutableStateFlow<ProfileScreenState>(value = ProfileScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    private val _pageEffect = MutableSharedFlow<ProfileScreenEffect>()
    val pageEffect = _pageEffect.asSharedFlow()

    fun processEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.OnInitProfile -> {
                checkIsUserAuth()
            }
            is ProfileScreenEvent.OnLogOutTabClick -> logOutUser()

            is ProfileScreenEvent.OnLogInBtnClick -> profileNavigator.toLogInScreen()
            is ProfileScreenEvent.OnSignUpBtnClick -> profileNavigator.toSighUpScreen()
        }
    }

    private fun checkIsUserAuth() {
        viewModelScope.launch {
            runCatching {
                checkIsUserAuthUseCase()
            }.onSuccess { isAuth ->
                if (isAuth) {
                    getUserProfile()
                } else {
                    _pageState.value = ProfileScreenState.Unauthorized
                }
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ProfileScreenEffect.Message(
                        message = messageResId
                    )
                )
            }
        }
    }

    private fun getUserProfile() {
        //для теста
        //_pageState.value = ProfileScreenState.ProfileResult(result = ProfileModel(1, "Вася Пупкин", "88005555535"))
        viewModelScope.launch {
            runCatching {
                _pageState.value = ProfileScreenState.Loading
                //delay(2000)
                getProfileUseCase()
            }.onSuccess { result ->
                _pageState.value = ProfileScreenState.ProfileResult(result = result)
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ProfileScreenEffect.Message(
                        message = messageResId
                    )
                )
            }
        }
    }

    private fun logOutUser() {
        viewModelScope.launch {
            runCatching {
                logOutUseCase()
            }.onSuccess {
                profileNavigator.back()
            }.onFailure { exception ->
                val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                _pageEffect.emit(
                    ProfileScreenEffect.Message(
                        message = messageResId
                    )
                )
            }
        }
    }
}