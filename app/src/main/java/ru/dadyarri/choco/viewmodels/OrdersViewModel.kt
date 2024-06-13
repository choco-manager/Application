package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.orders.services.OrdersService
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.ui.actions.OrdersAction
import ru.dadyarri.choco.ui.state.OrdersState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val navigationHandler: NavigationHandler,
    private val ordersService: OrdersService,
) : ViewModel() {

    private val _state = MutableStateFlow(OrdersState())
    val state = _state.asStateFlow()

    init {
        onRefresh()
    }

    fun onAction(action: OrdersAction) {
        when (action) {
            is OrdersAction.Refresh -> onRefresh()
            is OrdersAction.OpenOrder -> onOpenOrder(action.id)
            is OrdersAction.Create -> onCreate()
        }
    }

    private fun onOpenOrder(id: UUID) {
        viewModelScope.launch {
            navigationHandler.navigate(Route.Order(id))
        }
    }

    private fun onRefresh() {
        viewModelScope.launch {
            val response = ordersService.getAllOrders()

            if (response is Resource.Success) {
                _state.update {
                    it.copy(
                        data = response
                    )
                }
            }
        }
    }

    private fun onCreate() {
        viewModelScope.launch {
            navigationHandler.navigate(Route.CreateOrder)
        }
    }

}