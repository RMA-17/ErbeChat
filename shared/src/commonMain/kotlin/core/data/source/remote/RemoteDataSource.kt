package core.data.source.remote

import core.data.source.remote.model.auth.UserDtoItem
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

    suspend fun signUpUser(
        username: String,
        name: String,
        password: String
    ) {
        client.gotrue.signUpWith(
            Email
        ) {
            email = generateEmail()
            this.password = password
            data = buildJsonObject {
                put("username", username)
                put("name", name)
            }
        }
    }

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

    private fun generateEmail(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var result = ""
        for (i in 1..24) {
            result += chars[Random.nextInt(chars.length)]
        }
        return "$result@rabbaanii.net"
    }
}