package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.domain.orders.data.ListOfOrders

data class OrdersState(
    val title: UiText = UiText.StringResource(R.string.orders_screen),
    val data: Resource<ListOfOrders> = Resource.Loading(),
)