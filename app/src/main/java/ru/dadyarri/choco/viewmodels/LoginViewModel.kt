package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.ui.actions.LoginAction
import ru.dadyarri.choco.ui.form_fields.LoginFormField
import ru.dadyarri.choco.ui.state.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationHandler: NavigationHandler
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.Login -> onLogin()
            is LoginAction.ForgotPassword -> onForgotPassword()
            is LoginAction.TogglePasswordVisibility -> onTogglePasswordVisibility()
            is LoginAction.UpdateField<*> -> onUpdateField(action)
        }
    }


    private fun onLogin() {}

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


}