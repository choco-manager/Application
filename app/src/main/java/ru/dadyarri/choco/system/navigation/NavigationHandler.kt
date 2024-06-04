package ru.dadyarri.choco.system.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.dadyarri.choco.navigation.routes.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationHandler @Inject constructor() {
    private val _destination = MutableSharedFlow<Route>()
    val destination = _destination.asSharedFlow()

    suspend fun navigate(dest: Route) {
        _destination.emit(dest)
    }
}