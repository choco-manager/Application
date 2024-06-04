package ru.dadyarri.choco.ui.form_fields

sealed class RequestPasswordRestorationFormField<T> {
    data object Login : RequestPasswordRestorationFormField<String>()
}