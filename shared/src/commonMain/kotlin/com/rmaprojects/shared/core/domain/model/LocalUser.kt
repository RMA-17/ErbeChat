package com.rmaprojects.shared.core.domain.model

data class LocalUser(
    val bio: String,
    val name: String,
    val email: String,
    val id: String,
    val imageUrl: String,
    val username: String,
)