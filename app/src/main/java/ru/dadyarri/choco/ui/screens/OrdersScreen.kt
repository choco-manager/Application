package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.orders.data.ListOfOrders
import ru.dadyarri.choco.domain.orders.data.OrderDto
import ru.dadyarri.choco.domain.orders.data.OrderStatus
import ru.dadyarri.choco.domain.orders.data.PaymentStatus
import ru.dadyarri.choco.ui.actions.OrdersAction
import ru.dadyarri.choco.ui.components.orders.OrderCard
import ru.dadyarri.choco.ui.state.OrdersState
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun OrdersScreen(state: OrdersState, onAction: (OrdersAction) -> Unit) {

    if (state.data.data != null) {

        LazyColumn {
            items(state.data.data.orders, itemContent = {
                OrderCard(order = it) { id ->
                    onAction(OrdersAction.OpenOrder(id))
                }
            })
        }

    }

}

@Preview
@Composable
private fun OrdersScreenPreview() {
    OrdersScreen(
        state = OrdersState(
            data = Resource.Success(
                ListOfOrders(
                    listOf(
                        OrderDto(
                            id = UUID.randomUUID(),
                            orderedAt = LocalDateTime.now(),
                            orderStatus = OrderStatus.Processing,
                            products = emptyList(),
                            paymentStatus = PaymentStatus.Pending,
                            toBeDeliveredAt = LocalDateTime.now(),
                            totalAmount = 300.534
                        ),
                        OrderDto(
                            id = UUID.randomUUID(),
                            orderedAt = LocalDateTime.now().plusDays(2),
                            orderStatus = OrderStatus.OnHold,
                            products = emptyList(),
                            paymentStatus = PaymentStatus.Paid,
                            toBeDeliveredAt = LocalDateTime.now(),
                            totalAmount = 300.534
                        ),
                        OrderDto(
                            id = UUID.randomUUID(),
                            orderedAt = LocalDateTime.now(),
                            orderStatus = OrderStatus.Pending,
                            products = emptyList(),
                            paymentStatus = PaymentStatus.Paid,
                            toBeDeliveredAt = LocalDateTime.now(),
                            totalAmount = 300.534
                        ),
                        OrderDto(
                            id = UUID.randomUUID(),
                            orderedAt = LocalDateTime.now(),
                            orderStatus = OrderStatus.Processing,
                            products = emptyList(),
                            paymentStatus = PaymentStatus.Pending,
                            toBeDeliveredAt = LocalDateTime.now(),
                            totalAmount = 300.534
                        )
                    )
                )
            )
        ),
        onAction = {})
}