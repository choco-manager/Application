package ru.dadyarri.choco.ui.actions

sealed class RequestPasswordRestorationAction {
    data object Restore : RequestPasswordRestorationAction()
}