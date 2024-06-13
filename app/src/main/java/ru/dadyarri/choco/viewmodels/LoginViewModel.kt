package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.auth.data.LoginRequest
import ru.dadyarri.choco.domain.auth.services.AuthService
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.storage.DataStoreManager
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.ui.actions.LoginAction
import ru.dadyarri.choco.ui.form_fields.LoginFormField
import ru.dadyarri.choco.ui.state.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationHandler: NavigationHandler,
    private val authService: AuthService,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.Login -> onLogin()
            is LoginAction.Refresh -> onRefresh()
            is LoginAction.ForgotPassword -> onForgotPassword()
            is LoginAction.TogglePasswordVisibility -> onTogglePasswordVisibility()
            is LoginAction.UpdateField<*> -> onUpdateField(action)
        }
    }


    private fun onLogin() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isRefreshing = true
                )
            }

            val token = FirebaseMessaging.getInstance().token.await()

            val loginResponse = authService.login(
                LoginRequest(
                    login = _state.value.login,
                    password = _state.value.password,
                    fcmToken = token
                )
            )

            if (loginResponse is Resource.Success) {
                dataStoreManager.updateAccessToken(loginResponse.data!!.accessToken)
                dataStoreManager.updateRefreshToken(loginResponse.data.refreshToken)
                navigationHandler.navigate(Route.Orders)
            }
            _state.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }

    private fun onForgotPassword() {
        viewModelScope.launch {
            navigationHandler.navigate(Route.RequestPasswordRestoration)
        }
    }

    private fun onTogglePasswordVisibility() {
        _state.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    private fun <T> onUpdateField(action: LoginAction.UpdateField<T>) {
        when (action.field) {
            is LoginFormField.Login -> {
                _state.update {
                    it.copy(
                        login = action.newValue as String
                    )
                }
            }

            is LoginFormField.Password -> {
                _state.update {
                    it.copy(
                        password = action.newValue as String
                    )
                }
            }
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isRefreshing = true
                )
            }

            delay(500)

            _state.update {
                it.copy(
                    isRefreshing = false
                )
            }
        }
    }


}