package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dadyarri.choco.ui.actions.OrdersAction
import ru.dadyarri.choco.ui.state.OrdersState
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(OrdersState())
    val state = _state.asStateFlow()

    fun onAction(action: OrdersAction) {
        when (action) {
            is OrdersAction.OpenOrder -> onOpenOrder(action.id)
        }
    }

    private fun onOpenOrder(id: UUID) {

    }

}