package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.dadyarri.choco.ui.actions.RestorePasswordAction
import ru.dadyarri.choco.ui.form_fields.RestorePasswordFormField
import ru.dadyarri.choco.ui.state.RestorePasswordState
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RestorePasswordState())
    val state = _state.asStateFlow()

    fun onAction(action: RestorePasswordAction) {
        when (action) {
            is RestorePasswordAction.Restore -> onRestore()
            is RestorePasswordAction.UpdateField<*> -> onUpdateField(action)
            is RestorePasswordAction.TogglePasswordVisibility -> onToggleVisibility()
        }
    }

    private fun onToggleVisibility() {
        _state.update {
            it.copy(
                isPasswordVisible = !it.isPasswordVisible
            )
        }
    }

    private fun <T> onUpdateField(action: RestorePasswordAction.UpdateField<T>) {
        when (action.field) {
            is RestorePasswordFormField.Password -> {
                _state.update {
                    it.copy(
                        password = action.newValue as String
                    )
                }
            }

            is RestorePasswordFormField.RepeatPassword -> {
                _state.update {
                    it.copy(
                        repeatPassword = action.newValue as String
                    )
                }
            }
        }
    }

    private fun onRestore() {

    }

}