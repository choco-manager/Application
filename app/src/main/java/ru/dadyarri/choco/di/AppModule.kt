package ru.dadyarri.choco.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ru.dadyarri.choco.networking.AuthManager
import ru.dadyarri.choco.networking.HttpClientFactory
import ru.dadyarri.choco.storage.DataStoreManager
import ru.dadyarri.choco.system.navigation.NavigationHandler
import ru.dadyarri.choco.system.snackbar.SnackbarMessageHandler
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSnackbarMessageHandler(@ApplicationContext context: Context): SnackbarMessageHandler {
        return SnackbarMessageHandler(context)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthManager(@ApplicationContext context: Context): AuthManager {
        return AuthManager(context, provideDataStoreManager(context), provideHttpClient())
    }

    @Provides
    @Singleton
    fun provideNavigationHandler(): NavigationHandler {
        return NavigationHandler()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory.httpClient
    }
}