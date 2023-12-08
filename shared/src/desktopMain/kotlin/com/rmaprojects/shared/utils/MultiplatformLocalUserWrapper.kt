package com.rmaprojects.shared.utils

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual class MultiplatformLocalUserWrapper {
    actual fun createLocalUserPref(): ObservableSettings {
        val preferences = Preferences.userRoot()
        return PreferencesSettings(preferences)
    }
}