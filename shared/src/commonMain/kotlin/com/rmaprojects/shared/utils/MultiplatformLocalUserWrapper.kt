package com.rmaprojects.shared.utils

import com.russhwolf.settings.ObservableSettings

expect class MultiplatformLocalUserWrapper {
    fun createLocalUserPref(): ObservableSettings
}