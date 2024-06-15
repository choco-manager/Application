package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dadyarri.choco.common.Resource
import ru.dadyarri.choco.domain.orders.services.OrdersService
import ru.dadyarri.choco.ui.actions.OrderAction
import ru.dadyarri.choco.ui.state.OrderState
import java.util.UUID

@HiltViewModel(assistedFactory = OrderViewModel.Factory::class)
class OrderViewModel @AssistedInject constructor(
    private val ordersService: OrdersService,
    @Assisted private val id: UUID,
) : ViewModel() {

    private val _state = MutableStateFlow(OrderState())
    val state = _state.asStateFlow()

    @AssistedFactory
    interface Factory {
        fun create(id: UUID): OrderViewModel
    }

    init {
        onRefresh()
    }

    fun onAction(action: OrderAction) {
        when (action) {
            is OrderAction.Edit -> onEdit()
            is OrderAction.Refresh -> onRefresh()
        }
    }

    private fun onEdit() {

    }

    private fun onRefresh() {
        viewModelScope.launch {
            val response = ordersService.getOrder(id)

            if (response is Resource.Success) {
                _state.update {
                    it.copy(data = Resource.Success(response.data!!))
                }
            }
        }
    }

}