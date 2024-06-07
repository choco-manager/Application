package ru.dadyarri.choco.ui.state

import ru.dadyarri.choco.R
import ru.dadyarri.choco.common.UiText

data class OrdersState(
    val title: UiText = UiText.StringResource(R.string.orders_screen),
    val isRefreshing: Boolean = false,
)