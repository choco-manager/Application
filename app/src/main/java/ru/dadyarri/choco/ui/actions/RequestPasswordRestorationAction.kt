package ru.dadyarri.choco.ui.actions

import ru.dadyarri.choco.ui.form_fields.RequestPasswordRestorationFormField

sealed class RequestPasswordRestorationAction {
    data object RequestRestoration : RequestPasswordRestorationAction()
    data class UpdateField<T>(val field: RequestPasswordRestorationFormField<T>, val newValue: T) :
        RequestPasswordRestorationAction()
}