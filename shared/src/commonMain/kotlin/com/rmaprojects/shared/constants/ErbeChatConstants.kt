package com.rmaprojects.shared.constants

const val APP_NAME = "Erbe Chat"
const val LOCAL_USER_PREF = "user_local"

sealed class LocalKeys {
    object LocalUserPrefKeys {
        const val BIO: String = "bio"
        const val EMAIL: String = "email"
        const val ID: String = "local_id"
        const val IMAGE_URL: String = "img_url"
        const val NAME: String = "name"
        const val USERNAME: String = "username"
    }

    object SettingsPrefKeys {
        const val isDarkMode: String = "local_isDarkMode"
    }
}