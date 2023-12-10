package com.rmaprojects.shared.di

import com.rmaprojects.composeMain.BuildKonfig
import com.rmaprojects.shared.core.data.repository.ChatRepositoryImpl
import com.rmaprojects.shared.core.data.source.local.LocalDataSource
import com.rmaprojects.shared.core.data.source.remote.RemoteDataSource
import com.rmaprojects.shared.core.domain.repository.ChatRepository
import com.rmaprojects.shared.core.presentation.ui.screen.splash.SplashViewModel
import com.rmaprojects.shared.core.data.repository.AuthRepositoryImpl
import com.rmaprojects.shared.core.domain.interactor.UseCaseInteractor
import com.rmaprojects.shared.core.domain.repository.AuthRepository
import com.rmaprojects.shared.core.domain.usecase.ErbeChatUseCase
import com.rmaprojects.shared.features.auth.screen.login.LoginViewModel
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

    companion object {
        fun coreModule(): Module = module {
            single<SupabaseClient> { provideSupabaseClient() }
            singleOf(::RemoteDataSource)
            singleOf(::LocalDataSource)
            single<ChatRepository> { ChatRepositoryImpl(get()) }
            single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
            single<ErbeChatUseCase> { UseCaseInteractor(get()) }

        }

        fun featureModules(): Module = module {
            factory { LoginViewModel(get()) }
            factory { SplashViewModel(get()) }
        }

        private fun provideSupabaseClient(): SupabaseClient {
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
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            KoinModule.coreModule(),
            KoinModule.featureModules(),
            platformModule()
        )
    }
}

expect fun platformModule(): Module