package ru.dadyarri.choco.ui.components.orders

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.PauseCircle
import androidx.compose.material.icons.outlined.PendingActions
import androidx.compose.material.icons.outlined.Schema
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dadyarri.choco.R
import ru.dadyarri.choco.domain.orders.data.OrderDto
import ru.dadyarri.choco.domain.orders.data.OrderStatus
import ru.dadyarri.choco.domain.orders.data.PaymentStatus
import ru.dadyarri.choco.util.format
import java.util.UUID

@Composable
fun OrderCard(
    order: OrderDto,
    onClick: (UUID) -> Unit,
    onSelectOrderStatus: (UUID, OrderStatus) -> Unit,
    onSelectPaymentStatus: (UUID, PaymentStatus) -> Unit,
) {

    var orderStatusMenuStatus by remember { mutableStateOf(false) }
    var paymentStatusMenuStatus by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = {
            Text(stringResource(R.string.order_title, order.orderedAt.format()))
        },
        supportingContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                AssistChip(
                    onClick = {
                        orderStatusMenuStatus = true
                    },
                    label = { Text(getOrderStatusLabel(order.orderStatus)) },
                    leadingIcon = {
                        Icon(
                            imageVector = getOrderStatusIcon(order.orderStatus),
                            contentDescription = null,
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
                AssistChip(
                    onClick = {
                        paymentStatusMenuStatus = true
                    },
                    label = { Text(getPaymentStatusLabel(order.paymentStatus)) },
                    leadingIcon = {
                        Icon(
                            imageVector = getPaymentStatusIcon(order.paymentStatus),
                            contentDescription = null,
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }
        },
        trailingContent = {
            Text(text = stringResource(R.string.price, order.totalAmount))
        },
        modifier = Modifier
            .clickable {
                onClick(order.id)
            }
    )

    DropdownMenu(
        expanded = orderStatusMenuStatus,
        onDismissRequest = { orderStatusMenuStatus = false }) {
        OrderStatus.entries.forEach {
            DropdownMenuItem(
                text = {
                    Text(text = getOrderStatusLabel(orderStatus = it))
                },
                onClick = { onSelectOrderStatus(order.id, it) },
                leadingIcon = { Icon(getOrderStatusIcon(orderStatus = it), null) }
            )
        }
    }

    DropdownMenu(
        expanded = paymentStatusMenuStatus,
        onDismissRequest = { paymentStatusMenuStatus = false }) {
        PaymentStatus.entries.forEach {
            DropdownMenuItem(
                text = {
                    Text(text = getPaymentStatusLabel(paymentStatus = it))
                },
                onClick = { onSelectPaymentStatus(order.id, it) },
                leadingIcon = { Icon(getPaymentStatusIcon(paymentStatus = it), null) }
            )
        }
    }
}

@Composable
private fun getOrderStatusLabel(orderStatus: OrderStatus): String {
    val status = when (orderStatus) {
        OrderStatus.Pending -> R.string.order_pending
        OrderStatus.Processing -> R.string.order_processing
        OrderStatus.Shipped -> R.string.order_shipped
        OrderStatus.Delivered -> R.string.order_delivered
        OrderStatus.Cancelled -> R.string.order_cancelled
        OrderStatus.OnHold -> R.string.order_onHold
    }

    return stringResource(id = status)
}

private fun getOrderStatusIcon(orderStatus: OrderStatus): ImageVector {
    return when (orderStatus) {
        OrderStatus.Pending -> Icons.Outlined.PendingActions
        OrderStatus.Processing -> Icons.Outlined.Schema
        OrderStatus.Shipped -> Icons.Outlined.LocalShipping
        OrderStatus.Delivered -> Icons.Outlined.Check
        OrderStatus.Cancelled -> Icons.Outlined.Cancel
        OrderStatus.OnHold -> Icons.Outlined.PauseCircle
    }
}

@Composable
private fun getPaymentStatusLabel(paymentStatus: PaymentStatus): String {
    val status = when (paymentStatus) {
        PaymentStatus.Pending -> R.string.payment_pending
        PaymentStatus.Paid -> R.string.payment_paid
    }

    return stringResource(id = status)
}

private fun getPaymentStatusIcon(paymentStatus: PaymentStatus): ImageVector {
    return when (paymentStatus) {
        PaymentStatus.Pending -> Icons.Outlined.PendingActions
        PaymentStatus.Paid -> Icons.Outlined.Paid
    }
}