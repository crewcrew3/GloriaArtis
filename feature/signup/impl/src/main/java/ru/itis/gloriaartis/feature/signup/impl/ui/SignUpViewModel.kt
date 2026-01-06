package ru.itis.gloriaartis.feature.signup.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.gloriaartis.api.ProfileNavigator
import ru.itis.gloriaartis.utils.ExceptionHandler
import ru.itis.gloriaartis.ui.model.AuthFormState
import ru.itis.gloriaartis.feature.signup.impl.domain.usecase.SignUpUserUseCase
import ru.itis.gloriaartis.feature.signup.impl.ui.mvi.SignUpScreenEffect
import ru.itis.gloriaartis.feature.signup.impl.ui.mvi.SignUpScreenEvent
import ru.itis.gloriaartis.feature.signup.impl.ui.mvi.SignUpScreenState
import ru.itis.gloriaartis.ui.R
import ru.itis.gloriaartis.utils.ScreenAnalytics
import ru.itis.gloriaartis.utils.ValidatorHelper
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase,
    private val profileNavigator: ProfileNavigator,
    private val exceptionHandler: ExceptionHandler,
) : ViewModel() {

    init {
        ScreenAnalytics.logScreen(this::class.java.simpleName)
    }

    private val _pageState = MutableStateFlow<SignUpScreenState>(value = SignUpScreenState.Initial)
    val pageState = _pageState.asStateFlow()

    private val _formState = MutableStateFlow(value = AuthFormState())
    val formState = _formState.asStateFlow()

    private val _pageEffect = MutableSharedFlow<SignUpScreenEffect>()
    val pageEffect = _pageEffect.asSharedFlow()

    fun processEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.OnSignUpBtnClick -> registerUser(
                nickname = event.nickname,
                phoneNumber = event.phoneNumber,
                password = event.password,
                repeatPassword = event.repeatPassword
            )

            is SignUpScreenEvent.OnFormFieldChanged -> {
                _formState.update { state ->
                    state.copy(
                        nickname = event.nickname ?: state.nickname,
                        password = event.password ?: state.password,
                        phoneNumber = event.phoneNumber ?: state.phoneNumber,
                        repeatPassword = event.repeatPassword ?: state.repeatPassword,
                    )
                }
            }
            is SignUpScreenEvent.OnBackBtnClick -> profileNavigator.back()
        }
    }

    private fun registerUser(
        nickname: String,
        phoneNumber: String,
        password: String,
        repeatPassword: String
    ) {
        viewModelScope.launch {
            if (validateRegisterForm(
                    nickname = nickname,
                    phoneNumber = phoneNumber,
                    password = password,
                    repeatPassword = repeatPassword,
                )
            ) {
                runCatching {
                    signUpUserUseCase(
                        nickname = nickname,
                        phoneNumber = phoneNumber,
                        password = password,
                        repeatPassword = repeatPassword
                    )
                }.onSuccess {
                    _pageEffect.emit(
                        SignUpScreenEffect.Message(
                            message = R.string.toast_msg_signup_successful
                        )
                    )
                    profileNavigator.back()
                }.onFailure { exception ->
                    val messageResId = exceptionHandler.handleExceptionMessage(exception.message)
                    _pageEffect.emit(
                        SignUpScreenEffect.Message(
                            message = messageResId
                        )
                    )
                }
            }
        }
    }

    private suspend fun validateRegisterForm(
        nickname: String,
        phoneNumber: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        var errorCounts = 0
        if (!ValidatorHelper.validateNickname(nickname)) {
            _pageEffect.emit(SignUpScreenEffect.ErrorNicknameInput)
            errorCounts++
        }
        if (!ValidatorHelper.validatePhoneNumber(phoneNumber)) {
            _pageEffect.emit(SignUpScreenEffect.ErrorPhoneNumberInput)
            errorCounts++
        }
        if (!ValidatorHelper.validatePassword(password)) {
            _pageEffect.emit(SignUpScreenEffect.ErrorPasswordInput)
            errorCounts++
        }
        if (password != repeatPassword) {
            _pageEffect.emit(SignUpScreenEffect.ErrorRepeatPasswordInput)
            errorCounts++
        }
        return errorCounts == 0
    }
}