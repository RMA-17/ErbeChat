package com.rmaprojects.shared.di

import com.rmaprojects.shared.utils.MultiplatformLocalUserWrapper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformLocalUserWrapper().createLocalUserPref() }
}