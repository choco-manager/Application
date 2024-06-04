package ru.dadyarri.choco.ui.form_fields

sealed class RestorePasswordFormField<T> {
    data object Password : RestorePasswordFormField<String>()
    data object RepeatPassword : RestorePasswordFormField<String>()
}