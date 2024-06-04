package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dadyarri.choco.ui.actions.RequestPasswordRestorationAction
import ru.dadyarri.choco.ui.state.RequestPasswordRestorationState
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor(
): ViewModel() {

    private val _state = MutableStateFlow(RequestPasswordRestorationState())
    val state = _state.asStateFlow()

    fun onAction(action: RequestPasswordRestorationAction) {

    }

}