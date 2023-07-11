package ru.dadyarri.choco

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.dadyarri.choco.core.model.data.DarkThemeConfig
import ru.dadyarri.choco.core.model.data.ServerConfig
import ru.dadyarri.choco.core.model.data.UserPreferences
import ru.dadyarri.choco.core.model.data.UserPreferencesRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) :
    ViewModel() {

    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow
    private var userPreferences: MutableStateFlow<UserPreferences>

    init {
        runBlocking(Dispatchers.IO) {
            userPreferences =
                MutableStateFlow(userPreferencesFlow.first())
        }
    }


    fun getPreferences(): MutableStateFlow<UserPreferences> {
        viewModelScope.launch {
            userPreferencesFlow.collect {
                userPreferences.value = it
            }
        }

        return userPreferences
    }

    fun showWholesalePrices(show: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.updateShowWholesalePrices(show)
        }
    }

    fun updateDarkThemeConfig(config: DarkThemeConfig) {
        viewModelScope.launch {
            userPreferencesRepository.updateDarkThemeConfig(config)
        }
    }

    fun updateServerConfig(config: ServerConfig) {
        viewModelScope.launch {
            userPreferencesRepository.updateServerConfig(config)
        }
    }

}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val userPreferences: UserPreferences) : MainActivityUiState
}