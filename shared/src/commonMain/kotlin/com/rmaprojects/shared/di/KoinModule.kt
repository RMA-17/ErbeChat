package com.rmaprojects.shared.di

import com.rmaprojects.composeMain.BuildKonfig
import com.rmaprojects.shared.core.data.repository.ChatRepositoryImpl
import com.rmaprojects.shared.core.data.source.local.LocalDataSource
import com.rmaprojects.shared.core.data.source.remote.RemoteDataSource
import com.rmaprojects.shared.core.domain.repository.ChatRepository
import com.rmaprojects.shared.features.auth.data.repository.AuthRepositoryImpl
import com.rmaprojects.shared.features.auth.domain.repository.AuthRepository
import com.rmaprojects.shared.features.auth.presentation.ui.screen.auth.login.LoginViewModel
import com.rmaprojects.shared.features.auth.presentation.ui.screen.auth.register.RegisterViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

class KoinModule {
    fun coreModule(): Module = module {
        single { provideSupabaseClient() }
        singleOf(::RemoteDataSource)
        singleOf(::LocalDataSource)
        single<ChatRepository> { ChatRepositoryImpl(get()) }
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    }

    fun featureModules(): Module = module {
        factory { LoginViewModel(get()) }
        factory { RegisterViewModel(get()) }
    }

    private fun provideSupabaseClient(): SupabaseClient  {
        return createSupabaseClient(
            BuildKonfig.API_URL,
            BuildKonfig.API_KEY
        ) {
            install(GoTrue)
            install(Postgrest)
            install(Realtime)
        }
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            KoinModule().coreModule(),
            platformModule()
        )
    }
}

expect fun platformModule(): Module