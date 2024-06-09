package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.dadyarri.choco.domain.auth.services.AuthService
import ru.dadyarri.choco.navigation.routes.Route
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.ui.state.SplashScreenState
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authService: AuthService,
    private val navigationHandler: NavigationHandler,
) : ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isVisible = true)
            }

//            val whoamiResponse = authService.whoami()
//
//            val route = when (whoamiResponse) {
//                is Resource.Success -> Route.Orders
//                else -> Route.Login
//            }
//

            val whoamiResponse = false

            val route = when (whoamiResponse) {
                true -> Route.Orders
                else -> Route.Login
            }

            delay(5000)

            navigationHandler.navigate(route)

            _state.update {
                it.copy(
                    isVisible = false
                )
            }
        }
    }

}