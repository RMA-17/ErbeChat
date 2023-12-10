package com.rmaprojects.shared.core.data.source.remote

import com.rmaprojects.shared.core.data.source.remote.model.auth.UserDtoItem
import com.russhwolf.settings.ObservableSettings
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.random.Random

class RemoteDataSource(
    private val client: SupabaseClient
) {

    suspend fun signInUser(
        username: String,
        password: String
    ): UserDtoItem {
        val getUser = client.postgrest[TBL_USERS]
            .select(
                single = true
            ) {
                UserDtoItem::username eq username
            }
            .decodeSingle<UserDtoItem>()

        client.gotrue.loginWith(
            Email
        ) {
            email = getUser.email
            this.password = password
        }

        return getUser
    }

    suspend fun signOutUser() {
        client.gotrue.logout()
    }
}