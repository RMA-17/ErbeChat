package com.rmaprojects.shared.core.data.source.local

import com.rmaprojects.shared.constants.LocalKeys
import com.rmaprojects.shared.core.data.source.remote.model.auth.UserDtoItem
import com.rmaprojects.shared.features.auth.domain.model.LocalUser
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class LocalDataSource(
    private val userDataStore: ObservableSettings
) {
    fun saveUserData(user: UserDtoItem) {
        with(LocalKeys.LocalUserPrefKeys) {
            userDataStore[BIO] = user.bio
            userDataStore[NAME] = user.name
            userDataStore[EMAIL] = user.email
            userDataStore[ID] = user.id
            userDataStore[IMAGE_URL] = user.imageUrl
            userDataStore[USERNAME] = user.username
        }
    }

    fun getUserData(): LocalUser {
        with(LocalKeys.LocalUserPrefKeys) {
            return LocalUser(
                userDataStore[BIO] ?: "",
                userDataStore[NAME] ?: "",
                userDataStore[EMAIL] ?: "",
                userDataStore[ID] ?: "",
                userDataStore[IMAGE_URL] ?: "",
                userDataStore[USERNAME] ?: "",
            )
        }
    }

    fun deleteUserData() {
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.BIO)
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.NAME)
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.EMAIL)
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.ID)
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.IMAGE_URL)
        userDataStore.remove(LocalKeys.LocalUserPrefKeys.USERNAME)
    }

}