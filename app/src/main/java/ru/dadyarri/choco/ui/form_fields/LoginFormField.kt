package ru.dadyarri.choco.ui.form_fields

sealed class LoginFormField<T> {
    data object Login: LoginFormField<String>()
    data object Password: LoginFormField<String>()
}