package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.dadyarri.choco.ui.actions.RequestPasswordRestorationAction
import ru.dadyarri.choco.ui.form_fields.RequestPasswordRestorationFormField
import ru.dadyarri.choco.ui.state.RequestPasswordRestorationState
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(RequestPasswordRestorationState())
    val state = _state.asStateFlow()

    fun onAction(action: RequestPasswordRestorationAction) {
        when (action) {
            is RequestPasswordRestorationAction.RequestRestoration -> onRequestRestoration()
            is RequestPasswordRestorationAction.UpdateField<*> -> onUpdateField(action)
        }
    }

    private fun <T> onUpdateField(action: RequestPasswordRestorationAction.UpdateField<T>) {
        when (action.field) {
            is RequestPasswordRestorationFormField.Login -> {
                _state.update {
                    it.copy(
                        login = action.newValue as String
                    )
                }
            }
        }
    }

    private fun onRequestRestoration() {

    }

}