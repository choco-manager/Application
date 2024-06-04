package ru.dadyarri.choco.ui.actions

sealed class RestorePasswordAction {
    data object Restore : RestorePasswordAction()
}