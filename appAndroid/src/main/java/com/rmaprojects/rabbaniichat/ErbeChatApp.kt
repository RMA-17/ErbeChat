package com.rmaprojects.rabbaniichat

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ErbeChatApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ErbeChatApp)
        }
    }
}