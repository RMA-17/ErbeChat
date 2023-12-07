package di

import core.data.repository.ChatRepositoryImpl
import core.data.source.remote.RemoteDataSource
import io.github.irgaly.kottage.Kottage
import io.github.irgaly.kottage.KottageEnvironment
import io.github.irgaly.kottage.platform.KottageContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

class KoinModule {
    fun getModules(): Module = module {
        single { provideSupabaseClient() }
        single {
            RemoteDataSource(get())
        }
        single {
            ChatRepositoryImpl(get())
        }
    }

    private fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            "",
            ""
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
        modules(KoinModule().getModules())
    }
}