package core.data.source.remote.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDtoItem(
    @SerialName("bio")
    val bio: String?,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("name")
    val name: String,
    @SerialName("role")
    val role: String,
    @SerialName("username")
    val username: String
)