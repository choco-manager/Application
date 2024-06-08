package ru.dadyarri.choco.ui.actions

import ru.dadyarri.choco.ui.form_fields.LoginFormField

sealed class LoginAction {
    data object Login : LoginAction()
    data object Refresh : LoginAction()
    data object ForgotPassword : LoginAction()
    data object TogglePasswordVisibility : LoginAction()
    data class UpdateField<T>(val field: LoginFormField<T>, val newValue: T) : LoginAction()
}