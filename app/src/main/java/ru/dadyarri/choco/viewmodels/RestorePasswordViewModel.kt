package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dadyarri.choco.ui.state.RestorePasswordState
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor(
): ViewModel() {

    private val _state = MutableStateFlow(RestorePasswordState())
    val state = _state.asStateFlow()

}