package ru.dadyarri.choco.storage

import android.content.Context
import androidx.datastore.dataStore

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)