package ru.dadyarri.choco.ui.actions

import ru.dadyarri.choco.domain.orders.data.OrderStatus
import ru.dadyarri.choco.domain.orders.data.PaymentStatus
import java.util.UUID

sealed class OrdersAction {
    data object Refresh : OrdersAction()
    data class OpenOrder(val id: UUID) : OrdersAction()
    data object Create : OrdersAction()
    data class ChangeOrderStatus(val id: UUID, val orderStatus: OrderStatus) : OrdersAction()
    data class ChangePaymentStatus(val id: UUID, val paymentStatus: PaymentStatus) : OrdersAction()
}