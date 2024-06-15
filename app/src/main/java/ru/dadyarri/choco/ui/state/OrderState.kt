package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.common.UiText
import ru.dadyarri.choco.domain.orders.data.ExtendedOrderDto

data class OrderState(
    val title: UiText = UiText.StringResource(R.string.order),
    val data: Resource<ExtendedOrderDto> = Resource.Loading(null),
)
