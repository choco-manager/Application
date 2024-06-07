package ru.dadyarri.choco.ui.actions

import java.util.UUID

sealed class OrdersAction {
    data class OpenOrder(val id: UUID) : OrdersAction()
}