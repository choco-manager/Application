package ru.dadyarri.choco.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.dadyarri.choco.system.network.ConnectivityObserver
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    connectivityObserver: ConnectivityObserver,
) : ViewModel() {

    val networkState = connectivityObserver.observe()

}