package com.rmaprojects.shared.core.data.source.local

import com.rmaprojects.shared.constants.LocalUserPrefKeys
import com.rmaprojects.shared.core.data.source.remote.model.auth.UserDtoItem
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.set

class LocalDataSource(
    private val userDataStore: ObservableSettings
) {
    fun saveUserData(user: UserDtoItem) {
        with(LocalUserPrefKeys) {
            userDataStore[BIO] = user.bio
            userDataStore[NAME] = user.name
            userDataStore[EMAIL] = user.email
            userDataStore[ID] = user.id
            userDataStore[IMAGE_URL] = user.imageUrl
            userDataStore[USERNAME] = user.username
        }
    }
}