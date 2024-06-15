package ru.dadyarri.choco.ui.actions

sealed class OrderAction {
    data object Edit : OrderAction()
    data object Refresh : OrderAction()
}