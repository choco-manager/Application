package ru.dadyarri.choco.ui.actions

import ru.dadyarri.choco.ui.form_fields.RestorePasswordFormField

sealed class RestorePasswordAction {
    data object Restore : RestorePasswordAction()
    data object TogglePasswordVisibility : RestorePasswordAction()
    data class UpdateField<T>(val field: RestorePasswordFormField<T>, val newValue: T) :
        RestorePasswordAction()
}