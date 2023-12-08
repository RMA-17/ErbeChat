package com.rmaprojects.erbechat

import android.app.Application
import com.rmaprojects.shared.di.initKoin
import com.rmaprojects.shared.utils.ContextUtils
import org.koin.android.ext.koin.androidContext

class ErbeChatApp: Application() {
    override fun onCreate() {
        super.onCreate()

        ContextUtils.setContext(this)

        initKoin {
            androidContext(this@ErbeChatApp)
        }
    }
}