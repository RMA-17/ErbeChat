package com.rmaprojects.shared.utils

import android.content.Context
import com.rmaprojects.shared.constants.LOCAL_USER_PREF
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class MultiplatformLocalUserWrapper {

    val context = ContextUtils.context

    actual fun createLocalUserPref(): ObservableSettings {
        val sharedPref =
            context.getSharedPreferences(LOCAL_USER_PREF, Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPref)
    }
}